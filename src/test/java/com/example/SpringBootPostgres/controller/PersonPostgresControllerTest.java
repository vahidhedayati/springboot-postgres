package com.example.SpringBootPostgres.controller;

import com.example.SpringBootPostgres.api.PersonPostgresController;
import com.example.SpringBootPostgres.dao.PostgresDBDaoService;
import com.example.SpringBootPostgres.model.Person;
import com.example.SpringBootPostgres.service.PersonPostgresService;
;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@WebMvcTest(PersonPostgresController.class)
public class PersonPostgresControllerTest {

  /*
   @InjectMocks
    private PersonPostgresController personPostgresController;

    @Before("")
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc =  MockMvcBuilders.standaloneSetup(personPostgresController).build();
    }
*/
    @MockBean
    private PersonPostgresService personPostgresService;

    @MockBean
    private PostgresDBDaoService PostgresDBDaoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFailCreate() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/personpostgres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Dave Wellington\" }")

        );
        action.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("person was not added"));

    }




    @Test
    void shouldCreatePerson() throws Exception {
        when(personPostgresService.addPerson(Mockito.any(Person.class))).thenReturn(true);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/personpostgres")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe\" }")
                        //.content("{\"id\": \""+ UUID.randomUUID()+"\", \"name\": \"Dave Wellington\" }")
        );
        action.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));

    }


    @Test
    void shouldReturnAllPeople() throws Exception {
        Person people = new Person(UUID.randomUUID(), "John Doe");
        when(personPostgresService.getUsers()).thenReturn(List.of(people));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personpostgres"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"));
    }



    @Test
    void shouldRemovePerson() throws Exception {
        when(personPostgresService.deletePerson(Mockito.any(UUID.class))).thenReturn(true);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/personpostgres/"+UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("success user has been removed"));
    }


    @Test
    void shouldFailRemovingPerson() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/personpostgres/"+UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("person was not removed"));
    }
}
