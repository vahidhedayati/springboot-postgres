package com.example.SpringBootPostgres.model;

/**
 * Student is a POJO (Plain Old Java Object)
 *
 * The Annotations of Entity and Table are all JPA (Java Persistence API)
 * oneToOne / ManyToMany are part of Hibernate
 *
 *
 *
 *      Spring DATA JPA
 *    Hibernate, MyBatis, apache commons DB Tools, Cayenne
 *    JPA (Java Persistence API)
 *        JDBC
 *     RelationalDB
 *
 *
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends User {

    int age;

    //Hibernate
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    Set<Course> courses = new HashSet<>();

    public Student() {
        super();
    }

    public Student(int id, String name, int age) {
        super(name,id);
        this.age = age;
    }
    public Student(int id, String name, int age,Address address) {
        super(name,id);
        this.age = age;
        this.address=address;
    }
    public Student(int id, String name, int age,Address address,Set<Course> courses ) {
        super(name,id);
        this.age = age;
        this.address=address;
        this.courses=courses;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
