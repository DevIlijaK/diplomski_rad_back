package com.diplomski.obavestavanje.nastavnika.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class JsonModel {
    private String thesisId;
    private String thesisType;
    private String thesisTitle;
    private Date thesisRegistrationDate;
    private Date thesisDateOfSubmission ;
    private Date thesisDateOfDefense;
    private Integer thesisGrade;
    private List<ThesisProfessorRole> thesisCommission;
    private Student student;
}
