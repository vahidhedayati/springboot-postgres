package com.example.SpringBootPostgres.dao;

import com.example.SpringBootPostgres.model.Person;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class PostgresDBDaoService implements PersonDao {


    private final JdbcTemplate jdbcTemplate;

    public PostgresDBDaoService( JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addPerson(UUID id, String name) {
        System.out.println("----> "+id+"___-"+name+"------------------------------------------------------------------");
        String insert = "insert into Person (id, name) values(?,?)";
        return jdbcTemplate.update(insert, id, name)==1;

    }

    @Override
    public boolean addPerson(Person person) {
        System.out.println("----> "+person.getName()+"------------------------------------------------------------------");
        return addPerson(UUID.randomUUID(), person.getName());

    }

    @Override
    public List<Person> getUsers() {
        return jdbcTemplate.query("select id, name from Person ", (result, i)-> {
           return new Person(UUID.fromString(result.getString("id")), result.getString("name"));
        });
    }


    @Override
    public Optional<Person> findUserById(UUID id) {
        List<Person> pp = jdbcTemplate.query("select id, name from Person where id='"+id+"'",  (result, i)-> {
            return new Person(UUID.fromString(result.getString("id")), result.getString("name"));
        });
        return Optional.ofNullable(DataAccessUtils.nullableSingleResult(pp));
    }

    @Override
    public Optional<Person> findUserByName(String name) {
        List<Person> pp = jdbcTemplate.query("select id, name from Person where name='"+name+"'",  (result, i)-> {
            return new Person(UUID.fromString(result.getString("id")), result.getString("name"));
        });
        return Optional.ofNullable(DataAccessUtils.nullableSingleResult(pp));
    }

    @Override
    public boolean deletePerson(UUID id) {
        //return false;
        Optional<Person> p = findUserById(id);
         if (p.isPresent()) {
             //p.ifPresent(person -> entityManager.remove(person));
             String insert = "delete from person where id = ?";
             return jdbcTemplate.update(insert, id)==1;

         }
         return false;
    }

    @Override
    public boolean updatePerson(UUID id, Person person) {
        return false;
    }
}
