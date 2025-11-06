package com.spring.backend.springboot_backend_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;

@Data
@Entity
@Table(name="employees")
public class Employee {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @Column
    private double salary;

    @Column
    private String department;
}
