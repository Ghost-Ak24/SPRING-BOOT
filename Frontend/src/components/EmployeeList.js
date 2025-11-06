import React, { useEffect, useState } from 'react';
import EmployeeService from '../services/EmployeeService';
import { Link } from 'react-router-dom';

function EmployeeList() {
    const [employees, setEmployees] = useState([]);
    const [searchId, setSearchId] = useState("");

    useEffect(() => {
        loadAllEmployees();
    }, []);

    const loadAllEmployees = () => {
        EmployeeService.getEmployees().then((res) => {
            setEmployees(res.data);
        });
    };

    const deleteEmployee = (id) => {
        const confirmed = window.confirm("Are you sure you want to delete this employee?");
        if (confirmed) {
            EmployeeService.deleteEmployee(id).then(() => {
                setEmployees(employees.filter(emp => emp.id !== id));
            });
        }
    };

    const handleSearch = () => {
        if (searchId.trim() === "") return;

        EmployeeService.getEmployeeById(searchId)
            .then(response => {
                setEmployees([response.data]);  // show only matched employee
            })
            .catch(error => {
                alert("Employee not found!");
                console.error(error);
            });
    };

    return (
        <div className="mt-3">
            <h2 className="text-center">Employees List</h2>

            <div className="d-flex mb-3">
                <input
                    type="text"
                    className="form-control w-25"
                    placeholder="Enter Employee ID"
                    value={searchId}
                    onChange={(e) => setSearchId(e.target.value)}
                />
                <button className="btn btn-primary ms-2" onClick={handleSearch}>Search</button>
                <button className="btn btn-secondary ms-2" onClick={loadAllEmployees}>Reset</button>
                <Link to="/add-employee" className="btn btn-success ms-auto">Add Employee</Link>
            </div>

            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Salary</th>
                        <th>Department</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.length > 0 ? (
                        employees.map(emp =>
                            <tr key={emp.id}>
                                <td>{emp.firstName}</td>
                                <td>{emp.lastName}</td>
                                <td>{emp.email}</td>
                                <td>{emp.salary}</td>
                                <td>{emp.department}</td>
                                <td>
                                    <Link className="btn btn-info" to={`/edit-employee/${emp.id}`}>Edit</Link>
                                    &nbsp;
                                    <button className="btn btn-danger" onClick={() => deleteEmployee(emp.id)}>Delete</button>
                                    &nbsp;
                                    <Link className="btn btn-secondary" to={`/view-employee/${emp.id}`}>View</Link>
                                </td>
                            </tr>
                        )
                    ) : (
                        <tr>
                            <td colSpan="6" className="text-center">No employees found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default EmployeeList;
