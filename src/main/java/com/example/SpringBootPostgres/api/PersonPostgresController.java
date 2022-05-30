package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.HttpResponse;
import com.example.SpringBootPostgres.dao.PostgresDBDaoService;
import com.example.SpringBootPostgres.dto.PersonDto;
import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonPostgresService;
import com.example.SpringBootPostgres.service.PersonService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @Operation(
            tags = {"PersonPostgresController", "postgres2222"},
            operationId = "add Person to postgresDB",
            description = "Some form of description for adding person to postgresDB",
            summary = "this function will add a person to postgresDB using personPostgresService",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Custom Desc for body, please ensure a name is provided for this to work, ID can be removed",
            content = @Content(schema=@Schema(implementation = PersonDto.class))),
            externalDocs = @ExternalDocumentation(url="https://github.com/vahidhedayati/springboot-postgres", description = "For more details click below link"),
            responses = {@ApiResponse(responseCode = "200", content =
                                @Content(
                                        schema=@Schema(implementation = Person.class),
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        examples = {
                                                @ExampleObject(name="Success 1", value = "Object"),
                                                @ExampleObject(name="Success 2", value = "String"),
                                        }
                                        ),
                                        description = "success response"
                                )
                        }

    )
    ResponseEntity<?> addPersonPostgres(@Valid @NonNull @RequestBody  Person person) {
        System.out.println("----------------------------------------> "+person.getName()+"----------------------------------------::"+personPostgresService.addPerson(person));


        boolean added = personPostgresService.addPerson(person);
        if (!added) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not added"));
        }
        return ResponseEntity.ok(person);

    }

    @PostMapping("/test")
    @Operation(
            tags = {"PersonPostgresController"},
           parameters = {@Parameter(name="canEdit", description = "This is a dummy parameter to show parameter description for openAi", example="yes", in = ParameterIn.PATH)},
            security = {@SecurityRequirement(name="OurBearerJWT")}

    )
    ResponseEntity<?> addPersonWithParameter(@Valid @NonNull @RequestBody  Person person, String canEdit) {
        System.out.println("----------------------------------------> "+person.getName()+"----------------------------------------::"+personPostgresService.addPerson(person));


        boolean added = personPostgresService.addPerson(person);
        if (!added) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not added"));
        }
        return ResponseEntity.ok(person);

    }
    @GetMapping
    @Operation(
            tags = {"PersonPostgresController"}
    )
    ResponseEntity<?> getPostGresUsers() {
        List<Person> users =  personPostgresService.getUsers();
        if (users.size()>0) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().body(new HttpResponse("no users found"));
    }

    @GetMapping(path= "{id}")
    @Operation(
            tags = {"PersonPostgresController"}//,
            //parameters = {@Parameter(name="id", description = "id of item that we are getting", example="3fa85f64-5717-4562-b3fc-2c963f66afa6", in = ParameterIn.QUERY)}
    )
    ResponseEntity<?> getUser(@PathVariable("id") UUID id) {
        Person person =  personPostgresService.findById(id).orElse(null);
        if (person==null) {
            return ResponseEntity.badRequest().body(new HttpResponse("user not found"));
        }
        return ResponseEntity.ok(person);

    }



    @DeleteMapping(path="{id}")
    @Operation(
            tags = {"PersonPostgresController"}

    )
    ResponseEntity<?> deletePerson(@PathVariable("id")  UUID id) {

        boolean removed = personPostgresService.deletePerson(id);
        if (!removed) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not removed"));
        }
        return ResponseEntity.ok(new HttpResponse("success user has been removed"));
    }

    @DeleteMapping(path="/hidden/{id}")
    @Operation(
            tags = {"PersonPostgresController"},
            hidden = true
    )
    ResponseEntity<?> hiddenDeletePerson(@PathVariable("id")  UUID id) {

        boolean removed = personPostgresService.deletePerson(id);
        if (!removed) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not removed"));
        }
        return ResponseEntity.ok(new HttpResponse("success user has been removed"));
    }
    @PutMapping(path="{id}")
    @Operation(
            tags = {"PersonPostgresController"}
    )
    ResponseEntity<?> updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        boolean updated = personPostgresService.updatePerson(id, person);
        if (!updated) {
            return ResponseEntity.badRequest().body(new HttpResponse("person was not updated"));
        }
        return ResponseEntity.ok(person);
    }

}
