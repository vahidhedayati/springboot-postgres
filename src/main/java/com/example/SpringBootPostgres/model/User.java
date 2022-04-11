package com.example.SpringBootPostgres.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue("USER")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer status;

    public User() {
        super();
    }

    public User(String name, Integer status) {
        super();
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
