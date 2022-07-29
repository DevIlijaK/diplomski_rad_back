package com.diplomski.obavestavanje.nastavnika.Controller;



import com.diplomski.obavestavanje.nastavnika.Model.JsonModel;
import com.diplomski.obavestavanje.nastavnika.Model.Thesis;

import com.diplomski.obavestavanje.nastavnika.Service.ParsingService;
import com.diplomski.obavestavanje.nastavnika.Service.ThesisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("thesis")
public class ThesisController {


    @Autowired
    ThesisService thesisService;


    @GetMapping("/find{startPeriod}{endPerion}")
    public List<Thesis> returnAllByThesisDateOfDefenseBetween(
            @RequestParam("startPeriod") Date startPeriod,
            @RequestParam("endPerion")Date endPerion){
        System.out.println("startPeriod");
        return thesisService.returnAllByThesisDateOfDefenseBetween(startPeriod,endPerion);

    }
}
