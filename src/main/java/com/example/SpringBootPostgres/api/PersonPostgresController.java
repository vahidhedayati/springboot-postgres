package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonPostgresService;
import com.example.SpringBootPostgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/personpostgres")
@RestController
public class PersonPostgresController {

    private final PersonPostgresService personPostgresService;

    @Autowired
    public PersonPostgresController(PersonPostgresService personPostgresService) {

        this.personPostgresService = personPostgresService;
    }

    @PostMapping
    public boolean addPersonPostgres(@Valid @NonNull @RequestBody  Person person) {
        return personPostgresService.addPerson(person);
    }


    @GetMapping
    public List<Person> getPostGresUsers() {
        return personPostgresService.getUsers();
    }

    @GetMapping(path= "{id}")
    public Person getUser(@PathVariable("id") UUID id) {
        return personPostgresService.findById(id).orElse(null);
    }



    @DeleteMapping(path="{id}")
    public boolean deletePerson(@PathVariable("id")  UUID id) {
        return personPostgresService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public boolean updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        return personPostgresService.updatePerson(id, person);
    }

}
