package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public boolean addPerson(@RequestBody  Person person) {
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getUsers() {
        return personService.getUsers();
    }

    @GetMapping(path= "{id}")
    public Person getUser(@PathVariable("id") UUID id) {
        return personService.findById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public boolean deletePerson(@PathVariable("id")  UUID id) {
        return personService.deletePerson(id);
    }
    @PutMapping(path="{id}")
    public boolean updatePerson2(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

}
