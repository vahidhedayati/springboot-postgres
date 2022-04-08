package com.example.SpringBootPostgres.dao;

import com.example.SpringBootPostgres.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    boolean addPerson(UUID id, String name);

    default boolean addPerson(String name) {
        return addPerson(UUID.randomUUID(), name);
    }

    boolean addPerson(Person person);

    List<Person> getUsers();
    //public Person findUserViaMapById(UUID id);
    public Optional<Person> findUserById(UUID id);
    public Optional<Person> findUserByName(String name);
    boolean deletePerson(UUID id);
    boolean updatePerson(UUID id, Person person);
}
