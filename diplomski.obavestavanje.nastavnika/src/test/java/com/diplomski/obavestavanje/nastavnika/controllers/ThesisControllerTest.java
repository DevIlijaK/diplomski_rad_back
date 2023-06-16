package com.diplomski.obavestavanje.nastavnika.controllers;

import com.diplomski.obavestavanje.nastavnika.controller.ThesisController;
import com.diplomski.obavestavanje.nastavnika.model.JsonModel;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import com.diplomski.obavestavanje.nastavnika.service.ParsingService;
import com.diplomski.obavestavanje.nastavnika.service.ThesisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ThesisController.class)
@AutoConfigureMockMvc
public class ThesisControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ParsingService parsingService;

    @Mock
    private ThesisService thesisService;

    @InjectMocks
    private ThesisController yourController;

    @Autowired
    private ObjectMapper objectMapper;
    private static final String JSON_URL = "http://localhost:3000/thesis";
//    public List<Thesis> generateDummyTheses() {
//        List<Thesis> dummyTheses = new ArrayList<>();

        // Create three dummy Thesis objects
//        Thesis thesis1 = new Thesis(1L, "Type 1", "Title 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 90);
//        Thesis thesis2 = new Thesis(2L, "Type 2", "Title 2", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 85);
//        Thesis thesis3 = new Thesis(3L, "Type 3", "Title 3", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 95);

        // Add the dummy theses to the list
//        dummyTheses.add(thesis1);
//        dummyTheses.add(thesis2);
//        dummyTheses.add(thesis3);

//        return dummyTheses;
//    }

    @Test
    public void testMain() {
        // Mock the behavior of the parsing service
        JsonNode jsonNode = objectMapper.createObjectNode();  // Create a sample JsonNode for testing
        when(parsingService.parse(any(String.class))).thenReturn(jsonNode);

        // Perform the method call
        yourController.main();

        // Verify that the necessary operations were performed
        verify(parsingService).parse(JSON_URL);
//        verify(thesisService).filterDuplicates(any(List.class));

        // You can further assert or verify the behavior as required
    }
}
