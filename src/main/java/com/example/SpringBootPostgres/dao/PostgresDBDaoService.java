package com.example.SpringBootPostgres.dao;

import com.example.SpringBootPostgres.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class PostgresDBDaoService implements PersonDao {

    @Override
    public boolean addPerson(UUID id, String name) {
        return false;
    }

    @Override
    public boolean addPerson(Person person) {
        return false;
    }

    @Override
    public List<Person> getUsers() {
        return null;
        ////   return List.of(new Person())
    }

    @Override
    public Person findUserViaMapById(UUID id) {
        return null;
    }

    @Override
    public Optional<Person> findUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> findUserByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean deletePerson(UUID id) {
        return false;
    }

    @Override
    public boolean updatePerson(UUID id, Person person) {
        return false;
    }
}
