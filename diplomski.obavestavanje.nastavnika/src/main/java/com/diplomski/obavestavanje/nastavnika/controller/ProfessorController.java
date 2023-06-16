package com.diplomski.obavestavanje.nastavnika.controller;

import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ProfessorController {

    private static final String JSON_URL = "http://localhost:3002/professors";

    private final RestTemplate restTemplate;
    private final ProfessorService professorService;


    @GetMapping("/professors")
    @Scheduled(cron = "*/10 * * * * *")
    public void fetchAllProfessors() {
        // Perform GET request to fetch all professors
        ProfessorDTO[] professorsDTO = restTemplate.getForObject(JSON_URL, ProfessorDTO[].class);
        List<Professor> professors = professorService.translateToProfessors(professorsDTO);
        professorService.saveOrUpdateProfessors(professors);
    }
}

