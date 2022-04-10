package com.example.SpringBootPostgres.dao;

import com.example.SpringBootPostgres.model.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> retrieve();
    public String insert(Student student);
    public String delete(int StudentId);
}
