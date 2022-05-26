package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.HttpResponse;
import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonPostgresService;
import com.example.SpringBootPostgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<?> addPersonPostgres(@Valid @NonNull @RequestBody  Person person) {
        boolean added =   personPostgresService.addPerson(person);
        if (!added) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not added"));
        }
        return ResponseEntity.ok(person);

    }


    @GetMapping
    ResponseEntity<?> getPostGresUsers() {
        List<Person> users =  personPostgresService.getUsers();
        if (users.size()>0) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().body(new HttpResponse("no users found"));
    }

    @GetMapping(path= "{id}")
    ResponseEntity<?> getUser(@PathVariable("id") UUID id) {
        Person person =  personPostgresService.findById(id).orElse(null);
        if (person==null) {
            return ResponseEntity.badRequest().body(new HttpResponse("user not found"));
        }
        return ResponseEntity.ok(person);

    }



    @DeleteMapping(path="{id}")
    ResponseEntity<?> deletePerson(@PathVariable("id")  UUID id) {

        boolean removed = personPostgresService.deletePerson(id);
        if (!removed) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not removed"));
        }
        return ResponseEntity.ok(new HttpResponse("success user has been removed"));
    }

    @PutMapping(path="{id}")
    ResponseEntity<?> updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        boolean updated = personPostgresService.updatePerson(id, person);
        if (!updated) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not updated"));
        }
        return ResponseEntity.ok(person);
    }

}
