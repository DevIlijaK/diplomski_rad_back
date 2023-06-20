package com.diplomski.obavestavanje.nastavnika.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThesisDTO {
    private Long thesisId;
    private String thesisType;
    private String thesisTitle;
    private String thesisRegistrationDate;
    private String thesisDateOfSubmission;
    private String thesisDateOfDefense;
    private Integer thesisGrade;
    private List<ThesisCommissionDTO> thesisCommission;
    private StudentDTO student;
}
