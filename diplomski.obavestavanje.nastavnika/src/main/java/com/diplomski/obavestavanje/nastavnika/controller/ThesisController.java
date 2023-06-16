package com.diplomski.obavestavanje.nastavnika.controller;



import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.mappers.ThesisMapper;
import com.diplomski.obavestavanje.nastavnika.model.JsonModel;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;

import com.diplomski.obavestavanje.nastavnika.service.ParsingService;
import com.diplomski.obavestavanje.nastavnika.service.ThesisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ThesisController {

    private static final String MAIN_PAGE = "main";
    private static final String JSON_URL = "http://localhost:3000/thesis";

    private final ParsingService parsingService;
    private final ObjectMapper mapper;
    private final ThesisService thesisService;

//    @GetMapping
//    @Scheduled(cron = "*/10 * * * * *")
//    public void main() {
//
//        JsonNode jsonNode = (JsonNode) parsingService.parse(JSON_URL);
//        System.out.println(jsonNode);
//        ThesisDTO thesisDTO = mapper.convertValue(jsonNode, ThesisDTO.class);
//        Thesis thesis = ThesisMapper.toThesis(thesisDTO);
//        System.out.println("THESISSSS: " + thesis);
//        List<Thesis> theses = thesisService.filterDuplicates(List.of(thesis));
//        System.out.println("THESISSSS: " + theses.get(0));
//    }
    @GetMapping("find/{startPeriod}/{endPerion}")
    public List<Thesis> returnAllByThesisDateOfDefenseBetween(
            @RequestParam("startPeriod") Date startPeriod,
            @RequestParam("endPerion")Date endPerion){
        return thesisService.returnAllByThesisDateOfDefenseBetween(startPeriod,endPerion);

    }
    @GetMapping("get")
    public List<Thesis> getThesis(){
        return thesisService.getAllThesis();
    }
}
