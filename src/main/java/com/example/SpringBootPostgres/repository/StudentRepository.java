package com.example.SpringBootPostgres.repository;

import com.example.SpringBootPostgres.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This CrudRepository is an implementation of spring-data-jpa
 *
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
