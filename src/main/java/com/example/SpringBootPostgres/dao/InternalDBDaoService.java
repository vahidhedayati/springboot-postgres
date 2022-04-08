package com.example.SpringBootPostgres.dao;

import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("internalDBDao")
public class InternalDBDaoService implements PersonDao {

    Logger logger = LoggerFactory.getLogger(InternalDBDaoService.class);

    private List<Person> LIST_DB = new ArrayList<>();
    private Stack<Person> STACK_DB = new Stack<>();
    private HashSet<Person> HASHSET_DB = new HashSet<>();
    private Map<UUID , Person> HASHMAP_DB = new HashMap<>();

    @Override
    public boolean addPerson(UUID id, String name) {
        Person p = new Person(id, name);
        return addPerson(p);
    }

    @Override
    public boolean addPerson(Person person) {
        if (HASHMAP_DB.get(person.getId())==null) {
            LIST_DB.add(person);
            STACK_DB.add(person);
            HASHSET_DB.add(person);
            HASHMAP_DB.put(person.getId(), person);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> getUsers() {
        return this.LIST_DB;
    }


    @Override
    public Person findUserViaMapById(UUID id) {
        return HASHMAP_DB.get(id);
    }

    @Override
    public Optional<Person> findUserById(UUID id) {
        Long t0=System.nanoTime();
        Optional<Person> p=  LIST_DB.stream().filter(person-> person.getId()==id).findFirst();
        logger.info("findUserById timed:  "+ Helper.estimateTime(System.nanoTime()-t0));
        return p;
    }

    Optional<Person> findUserViaStackById(UUID id) {
        return STACK_DB.stream().filter(person-> person.getId()==id).findFirst();
    }

    Optional<Person> findUserViaHashSetById(UUID id) {
        return HASHSET_DB.stream().filter(person-> person.getId()==id).findFirst();
    }

    @Override
    public Optional<Person> findUserByName(String name) {
        return LIST_DB.stream().filter(person-> person.getName()==name).findFirst();
    }

    @Override
    public boolean deletePerson(UUID id) {
        Optional<Person> person = findUserById(id);
        if (person.isPresent()) {
            Person p = person.get();
            if (HASHMAP_DB.get(p.getId()) != null) {
                HASHMAP_DB.remove(id);
                LIST_DB.remove(p);
                STACK_DB.remove(p);
                HASHSET_DB.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePerson(UUID id, Person person) {
        return findUserById(id).map(
                p-> {
                    int index = LIST_DB.indexOf(p);
                    if (index>=0) {
                        LIST_DB.set(index, person);
                        boolean res1= updateHashSetPerson(id,person);
                        boolean res2 = updatePersonViaMap(id,person);
                        boolean res3 = updateStackPerson(id,person);
                        if (res1 && res2 && res3) {
                            return true;
                        }
                    }
                    return false;
                }
        ).orElse(false);
    }

    public boolean updatePersonViaMap(UUID id, Person person) {
        if (HASHMAP_DB.get(id) != null) {
            HASHMAP_DB.put(id,person);
            return true;
        }
        return false;
    }

    public boolean updateHashSetPerson(UUID id, Person person) {
        return findUserViaHashSetById(id).map(
                p-> {
                    if (p.getId()!=null) {
                        HASHSET_DB.remove(p);
                        HASHSET_DB.add(person);
                        return true;
                    }
                    return false;
                }
        ).orElse(false);
    }

    public boolean updateStackPerson(UUID id, Person person) {
        return findUserViaStackById(id).map(
                p-> {
                    int index = STACK_DB.indexOf(person);
                    if (index >= 0) {
                        STACK_DB.set(index, person);
                        return true;
                    }
                    return false;
                }
        ).orElse(false);
    }
}
