package com.example.SpringBootPostgres.service;

import com.example.SpringBootPostgres.dao.StudentDao;
import com.example.SpringBootPostgres.model.Student;
import com.example.SpringBootPostgres.repository.StudentJpaRepository;
import com.example.SpringBootPostgres.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentDao {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentJpaRepository studentJpaRepository;


    public Optional<Student> findStudentByName(String name) {
        return studentJpaRepository.findOneByName(name);
    }



    @Override
    public Optional<Student> findStudentById(int id) {
       return studentRepository.findById(id);

    }

    @Override
    @Transactional
    public List<Student> retrieve() {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        return studentList;
    }


    @Override
    @Transactional
    public String insert(Student student) {
        Student save = studentRepository.save(student); // Call to the Repository
        if (save != null) {
            System.out.println("Student: "+student.getName()+"  has id: " + save.getId());
            return "Student: "+student.getName()+"  successfully added to database: "+save.getId();
        }
        return "Failed to save student to database";
    }

    @Override
    @Transactional
    public String delete(int StudentId) {
        studentRepository.deleteById(StudentId);
        return "Student "+StudentId+" successfully deleted from the database";
    }
    // Commit Transaction
}
