package com.diplomski.obavestavanje.nastavnika.mappers;

import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ThesisMapper {
    public static Thesis toThesis(ThesisDTO dto) {
        Thesis thesis = new Thesis();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        thesis.setThesisId(dto.getThesisId());
        thesis.setThesisType(dto.getThesisType());
        thesis.setThesisTitle(dto.getThesisTitle());
        thesis.setThesisRegistrationDate(LocalDateTime.parse(dto.getThesisRegistrationDate(), formatter));
        thesis.setThesisDateOfSubmission(LocalDateTime.parse(dto.getThesisDateOfSubmission(), formatter));
        thesis.setThesisDateOfDefense(LocalDateTime.parse(dto.getThesisDateOfDefense(), formatter));
        thesis.setThesisGrade(dto.getThesisGrade());

//        List<ThesisCommission> thesisCommissions = new ArrayList<>();
//        for (ThesisCommissionDTO commissionDTO : dto.getThesisCommission()) {
//            ThesisCommission commission = new ThesisCommission();
//            commission.setRole(commissionDTO.getRole());
//            commission.setThesis(thesis);
//            commission.setProfessor(ProfessorMapper.toProfessor(commissionDTO.getProfessor()));
//            thesisCommissions.add(commission);
//        }
//        thesis.setThesisCommission(thesisCommissions);
//
//        thesis.setStudent(StudentMapper.toStudent(dto.getStudent()));
        return thesis;
    }

    public static ThesisDTO toThesisDTO(Thesis thesis) {
        ThesisDTO dto = new ThesisDTO();
        dto.setThesisId(thesis.getThesisId());
        dto.setThesisType(thesis.getThesisType());
        dto.setThesisTitle(thesis.getThesisTitle());
        dto.setThesisRegistrationDate(String.valueOf(thesis.getThesisRegistrationDate()));
        dto.setThesisDateOfSubmission(String.valueOf(thesis.getThesisDateOfSubmission()));
        dto.setThesisDateOfDefense(String.valueOf(thesis.getThesisDateOfDefense()));
        dto.setThesisGrade(thesis.getThesisGrade());

        List<ThesisCommissionDTO> commissionDTOs = new ArrayList<>();
        for (ThesisCommission commission : thesis.getThesisCommission()) {
            ThesisCommissionDTO commissionDTO = new ThesisCommissionDTO();
            commissionDTO.setRole(commission.getRole());
            commissionDTO.setProfessor(ProfessorMapper.toProfessorDTO(commission.getProfessor()));
            commissionDTOs.add(commissionDTO);
        }
        dto.setThesisCommission(commissionDTOs);

        dto.setStudent(StudentMapper.toStudentDTO(thesis.getStudent()));
        return dto;
    }

    public static List<ThesisDTO> toThesisDTOList(List<Thesis> theses) {
        List<ThesisDTO> dtos = new ArrayList<>();
        for (Thesis thesis : theses) {
            dtos.add(toThesisDTO(thesis));
        }
        return dtos;
    }
}



