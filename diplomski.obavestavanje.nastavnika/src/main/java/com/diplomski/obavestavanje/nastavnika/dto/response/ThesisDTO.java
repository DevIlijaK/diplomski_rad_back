package com.diplomski.obavestavanje.nastavnika.dto.response;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ThesisDTO {
    private Long thesisId;
    private String thesisType;
    private String thesisTitle;
    private Date thesisRegistrationDate;
    private Date thesisDateOfSubmission;
    private Date thesisDateOfDefense;
    private Integer thesisGrade;
    private List<ThesisCommissionDTO> thesisCommission;
    private StudentDTO student;
}
