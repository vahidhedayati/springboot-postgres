package com.example.SpringBootPostgres.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {

    int age;

    public Student() {
        super();
    }

    public Student(int id, String name, int age) {
        super(name,id);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
