package com.diplomski.obavestavanje.nastavnika.Controller;



import com.diplomski.obavestavanje.nastavnika.Model.Thesis;

import com.diplomski.obavestavanje.nastavnika.Service.ParsingService;
import com.diplomski.obavestavanje.nastavnika.Service.ThesisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThesisController {

    private static final String MAIN_PAGE = "main";
    private static final String JSON_URL = "http://localhost:3000/diplomski";

    @Autowired
    private ParsingService parsingService;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ThesisService thesisService;

    @GetMapping
    public String main() {

        JsonNode jsonNode = (JsonNode) parsingService.parse(JSON_URL);

        List<Thesis> thesis = mapper.convertValue(jsonNode, new TypeReference<List<Thesis>>() { });
        System.out.println(thesis);
        thesis.forEach(oneThesis -> thesisService.saveThesis(oneThesis));
        return MAIN_PAGE;
    }
}
