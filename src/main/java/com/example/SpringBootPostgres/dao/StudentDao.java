package com.example.SpringBootPostgres.dao;

import com.example.SpringBootPostgres.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    public Optional<Student> findStudentById(int id);
    public List<Student> retrieve();
    public String insert(Student student);
    public String delete(int StudentId);
}
