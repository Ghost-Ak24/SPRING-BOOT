package com.spring.backend.springboot_backend_1.service.impl;

import com.spring.backend.springboot_backend_1.exception.ResourceNotFoundException;
import com.spring.backend.springboot_backend_1.service.EmployeeService;
import org.springframework.stereotype.Service;
import com.spring.backend.springboot_backend_1.model.Employee;
import com.spring.backend.springboot_backend_1.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;  // for database connection

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {   //depedency injection for jpa
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else
        {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id)
    {
        //we need to check whether the employee with given id exist or not there for seach in repsitorty, find by id return optional object
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
//        double existingSalary = existingEmployee.getSalary();
//        double newSalary = employee.getSalary();
//
//        if(newSalary > (existingSalary * 0.15))
//        {
//            throw new IllegalArgumentException("Salary incease cant be more than 15%");
//        }

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        //save existing employee to database
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }
}

