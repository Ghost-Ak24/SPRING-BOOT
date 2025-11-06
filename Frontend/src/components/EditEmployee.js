import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

function EditEmployee() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [employee, setEmployee] = useState({
    firstName: '',
    lastName: '',
    email: '',
    salary: '',
    department: ''
  });

  useEffect(() => {
    EmployeeService.getEmployeeById(id).then((res) => {
      setEmployee(res.data);
    });
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee({ ...employee, [name]: value });
  };

  const updateEmployee = (e) => {
    e.preventDefault();
    EmployeeService.updateEmployee(id, employee).then(() => {
      navigate('/employees');
    });
  };

  return (
    <div className="container mt-3">
      <h2>Edit Employee</h2>
      <form onSubmit={updateEmployee}>
        <div className="mb-3">
          <label>First Name</label>
          <input type="text" name="firstName" className="form-control"
            value={employee.firstName} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label>Last Name</label>
          <input type="text" name="lastName" className="form-control"
            value={employee.lastName} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label>Email</label>
          <input type="email" name="email" className="form-control"
            value={employee.email} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label>Salary</label>
          <input type="number" name="salary" className="form-control"
            value={employee.salary} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label>Department</label>
          <input type="text" name="department" className="form-control"
            value={employee.department} onChange={handleChange} />
        </div>

        <button className="btn btn-primary">Update</button>
      </form>
    </div>
  );
}

export default EditEmployee;
