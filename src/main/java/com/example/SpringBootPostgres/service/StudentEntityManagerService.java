package com.example.SpringBootPostgres.service;

import com.example.SpringBootPostgres.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentEntityManagerService {

    @Autowired
    EntityManager em;

    // Transaction starts at calling updateName and ends when leaving
    // It flushes as it leaves and therefore no save required
    public void updateName(int id, String name) {
        em.find(Student.class, id).setName(name);
    }

    /*
    public List<Student> findAllByAnAge(int byAge) {
        Student pattern = new Student(-1, null, byAge, null, null);
        Example<Student> strictMatch = Example.of(pattern, ExampleMatcher.matchingAll().withIgnoreCase());
        return em.findAll(strictMatch);
    }

     */
}
