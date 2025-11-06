package com.spring.backend.springboot_backend_1.service;

import com.spring.backend.springboot_backend_1.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee); //employee is object
    List<Employee> findAllEmployees();
    Employee findEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
