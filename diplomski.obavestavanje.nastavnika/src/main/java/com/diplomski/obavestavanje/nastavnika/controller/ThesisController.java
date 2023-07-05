package com.diplomski.obavestavanje.nastavnika.controller;


import com.diplomski.obavestavanje.nastavnika.dto.requests.FindThesesByProfessorAndDateRangeRequest;
import com.diplomski.obavestavanje.nastavnika.dto.requests.GetAppUsersRequest;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Slf4j
public class ThesisController {

    private static final String MAIN_PAGE = "main";
    private static final String JSON_URL = "http://localhost:3000/thesis";

    private final ParsingService parsingService;
    private final ObjectMapper mapper;
    private final ThesisService thesisService;

    @GetMapping("/thesis")
//    @Scheduled(cron = "*/10 * * * * *")
    public void main() {

        JsonNode jsonNode = parsingService.parse(JSON_URL);
        System.out.println(jsonNode);
        List<ThesisDTO> thesisDTOList = mapper.convertValue(jsonNode, new TypeReference<List<ThesisDTO>>() {
        });
        log.info("THESIS DTO: " + thesisDTOList);
        thesisService.setThesisWithCommissionAndStudent(thesisDTOList);
    }

    @GetMapping("find/{startPeriod}/{endPerion}")
    public List<Thesis> returnAllByThesisDateOfDefenseBetween(
            @RequestParam("startPeriod") Date startPeriod,
            @RequestParam("endPerion") Date endPerion) {
        return thesisService.returnAllByThesisDateOfDefenseBetween(startPeriod, endPerion);

    }

    @PostMapping("find/thesis/by/professor/and/date/range")
    public List<ThesisDTO> findThesesByProfessorAndDateRange(
            @RequestBody FindThesesByProfessorAndDateRangeRequest findThesesByProfessorAndDateRangeRequest) {
        List<ThesisDTO> thesesByProfessorAndDateRange = thesisService.findThesesByProfessorAndDateRange(
                findThesesByProfessorAndDateRangeRequest.getEmail(),
                findThesesByProfessorAndDateRangeRequest.getStartDate(),
                findThesesByProfessorAndDateRangeRequest.getEndDate()
        );
        log.info("THESIS DTO: " + thesesByProfessorAndDateRange);
        return thesesByProfessorAndDateRange;
    }

    @GetMapping("get")
    public List<Thesis> getThesis() {
        return thesisService.getAllThesis();
    }
}
