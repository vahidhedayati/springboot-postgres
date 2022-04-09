package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonPostgresService;
import com.example.SpringBootPostgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/people")
@Controller
public class PeopleController {

    private final PersonPostgresService personService;

    @Autowired
    public PeopleController(PersonPostgresService personService) {
        this.personService = personService;
    }


    @GetMapping
    public String listUsers(Model model) {
        List<Person> users = personService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", "admin");
        return "listUsers";
    }
}
