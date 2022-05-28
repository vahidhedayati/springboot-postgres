package com.example.SpringBootPostgres.cucumber.glue;


import com.example.SpringBootPostgres.DemoApplication;
import com.example.SpringBootPostgres.cucumber.CucumberIntegrationTest;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
@CucumberContextConfiguration
@SpringBootTest(classes = {DemoApplication.class, CucumberIntegrationTest.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberConfiguration {

    private final ObjectMapper objectMapper;


    public CucumberConfiguration() {
        objectMapper = new ObjectMapper();
    }

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(final Object from, final Type to) {
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }
}
