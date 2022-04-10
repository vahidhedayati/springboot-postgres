package com.example.SpringBootPostgres.repository;

import com.example.SpringBootPostgres.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
