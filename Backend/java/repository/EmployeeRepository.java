package com.spring.backend.springboot_backend_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.backend.springboot_backend_1.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
