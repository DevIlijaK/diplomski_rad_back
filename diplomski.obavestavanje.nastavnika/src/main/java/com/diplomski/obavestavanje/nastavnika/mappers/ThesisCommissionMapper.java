package com.diplomski.obavestavanje.nastavnika.mappers;

import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;

public class ThesisCommissionMapper {
    public static ThesisCommission toThesisCommission(ThesisCommissionDTO dto) {
        ProfessorDTO professorDTO = dto.getProfessor();
        Professor professor = ProfessorMapper.toProfessor(professorDTO);

        ThesisCommission thesisCommission = new ThesisCommission();
        thesisCommission.setProfessor(professor);
        thesisCommission.setRole(dto.getRole());

        return thesisCommission;
    }

    public static ThesisCommissionDTO toThesisCommissionDTO(ThesisCommission thesisCommission) {
        Professor professor = thesisCommission.getProfessor();
        ProfessorDTO professorDTO = ProfessorMapper.toProfessorDTO(professor);

        ThesisCommissionDTO dto = new ThesisCommissionDTO();
        dto.setProfessor(professorDTO);
        dto.setRole(thesisCommission.getRole());

        return dto;
    }
}
