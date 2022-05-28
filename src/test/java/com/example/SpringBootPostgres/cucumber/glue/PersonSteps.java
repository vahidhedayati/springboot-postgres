package com.example.SpringBootPostgres.cucumber.glue;

import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonPostgresService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PersonSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonPostgresService personPostgresService;

    private List<Person> expectedPeople;
    private List<Object> actualPeople;

    @Before
    public void setup() {

        expectedPeople = new ArrayList<>();
        actualPeople = new ArrayList<>();
        personPostgresService.deleteAll();
    }

    @Given("^the following people$")
    public void givenTheFollowingPeople(final List<Person> persons) {
       expectedPeople.addAll(persons);
       personPostgresService.saveAll(persons);
    }


    @When("^the user requests all the people$")
    public void userRequestsAllPeople() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        actualPeople.addAll( Arrays.asList( objectMapper.readValue(testRestTemplate.getForEntity("/api/v1/personpostgres", String.class).getBody(), Person[].class)));
    }

    @Then("^all the people are returned$")
    public void returnAllPeople() {
        validatePeople();
    }

    private void validatePeople() {
        System.out.println("-- > "+expectedPeople.size()+" -- "+actualPeople.size()+"---------------------------------------------------------------------------------");
        Assertions.assertEquals(expectedPeople.size(),actualPeople.size());
        IntStream.range(0,actualPeople.size()).forEach(index->validatePerson((Person)actualPeople.get(index),expectedPeople.get(index)));
    }
    private void validatePerson(final Person actual, final Person expected) {
        //Assertions.assertEquals(actual.getId(),expected.getId());
        Assertions.assertEquals(actual.getName(),expected.getName());
    }
}
