package com.spring.backend.springboot_backend_1.controller;

import com.spring.backend.springboot_backend_1.model.Employee;
import com.spring.backend.springboot_backend_1.service.EmployeeService;
import org.apache.commons.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //build create employee restapi
    @PostMapping
        public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //build get all employee Rest api
    @GetMapping
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    //build get employee by id
    ///"api/employee/e"
    @GetMapping("{id}")
    public ResponseEntity<Employee> findById(@PathVariable ("id") long employeeId)
    {
        return new ResponseEntity<Employee>(employeeService.findEmployeeById(employeeId), HttpStatus.OK);
    }

    //build update rest api
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id ,@RequestBody Employee employee)
    {
        //now we need to store the update api detail in object used request body
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id)
    {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully!");
    }
}
