package com.diplomski.obavestavanje.nastavnika.controllers;

import com.diplomski.obavestavanje.nastavnika.controller.StudentController;
import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RestClientTest
@Slf4j
public class StudentControllerTest {


    private static final String JSON_URL = "http://localhost:3000/students";

    @Test
    public void testGetStudents() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        // Mock the response
        String json = readFile("students.json"); // Read JSON file from resources folder
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        ResponseActions responseActions = mockServer.expect(MockRestRequestMatchers.requestTo(JSON_URL));
        responseActions.andRespond(MockRestResponseCreators.withSuccess(json, MediaType.APPLICATION_JSON));


        // Perform the test
        StudentDTO[] studentsDTO = restTemplate.getForObject(JSON_URL, StudentDTO[].class);
        log.info("STUDENTS" + Arrays.toString(studentsDTO));

        // Assertions
        assertThat(studentsDTO).isNotNull();
        assertThat(studentsDTO).hasSize(3); // Assuming there are 3 students in the JSON file
        // Add more assertions as needed
    }

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(new ClassPathResource(path).getURI())));
    }
}
