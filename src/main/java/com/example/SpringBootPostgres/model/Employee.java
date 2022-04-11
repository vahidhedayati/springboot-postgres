package com.example.SpringBootPostgres.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {

    private String email;
    private String department;

    public Employee(String name, Integer status, String email, String department) {
        super(name, status);
        this.email = email;
        this.department = department;
    }
}
