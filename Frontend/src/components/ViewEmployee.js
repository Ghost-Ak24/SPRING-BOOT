import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

function ViewEmployee() {
    const { id } = useParams();
    const [employee, setEmployee] = useState({});

    useEffect(() => {
        EmployeeService.getEmployeeById(id).then((res) => {
            setEmployee(res.data);
        });
    }, [id]);

    return (
        <div className="container mt-3">
            <h2>Employee Details</h2>
            <ul className="list-group">
                <li className="list-group-item"><b>First Name:</b> {employee.firstName}</li>
                <li className="list-group-item"><b>Last Name:</b> {employee.lastName}</li>
                <li className="list-group-item"><b>Email:</b> {employee.email}</li>
                <li className="list-group-item"><b>Salary:</b> {employee.salary}</li>
                <li className="list-group-item"><b>Department:</b> {employee.department}</li>
            </ul>
            <br />
            <Link to="/employees" className="btn btn-secondary">Back</Link>
        </div>
    );
}

export default ViewEmployee;
