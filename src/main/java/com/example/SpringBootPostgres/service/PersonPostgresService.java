package com.example.SpringBootPostgres.service;

import com.example.SpringBootPostgres.dao.PersonDao;
import com.example.SpringBootPostgres.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonPostgresService {

    private final PersonDao personDao;

    @Autowired
    public PersonPostgresService(@Qualifier("postgresDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public boolean addPerson(String personName) {
        System.out.println("--Via Name String "+personName+"------------------------------------------------------");
        return personDao.addPerson(personName);
    }
    public boolean addPerson(Person person) {
        System.out.println("--Via Person Object "+person.getName()+"------------------------------------------------------");
        return personDao.addPerson(person);
    }

    public List<Person> getUsers() {
        return personDao.getUsers();
    }

    public Optional<Person> findById(UUID id) {
        return personDao.findUserById(id);
    }

    public Optional<Person> findByName(String name) {
        return personDao.findUserByName(name);
    }

    public boolean deletePerson(UUID id) {
        return personDao.deletePerson(id);
    }

    public boolean updatePerson(UUID id, Person person) {
        return personDao.updatePerson(id, person);
    }

    public void saveAll(List<Person> people) {
        personDao.saveAll(people);
    }
    public void deleteAll() {
        personDao.deleteAll();
    }
}
