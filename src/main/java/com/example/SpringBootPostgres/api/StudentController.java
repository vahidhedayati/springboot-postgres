package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.dao.StudentDao;
import com.example.SpringBootPostgres.model.Student;
import com.example.SpringBootPostgres.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> retrieve() {
        List<Student> studentList = studentService.retrieve();
        return studentList;
    }

    @PostMapping
    public String insert(@RequestBody Student student) {
        if (student == null) {
            return "Failed to insert Student within the database";
        } else {
            String result = studentService.insert(student);
            return result;
        }
    }

    @DeleteMapping
    public String delete(int studentId) {
        String result = studentService.delete(studentId);
        return result;
    }
}
