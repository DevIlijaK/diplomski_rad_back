package com.diplomski.obavestavanje.nastavnika.mappers;

import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.model.Professor;

public class ProfessorMapper {
    public static Professor toProfessor(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setProfessorId(dto.getProfessorId());
        professor.setIdentificationNumber(dto.getIdentificationNumber());
        professor.setFullName(dto.getFullName());
        professor.setEmail(dto.getEmail());
        return professor;
    }

    public static ProfessorDTO toProfessorDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setProfessorId(professor.getProfessorId());
        dto.setIdentificationNumber(professor.getIdentificationNumber());
        dto.setFullName(professor.getFullName());
        dto.setEmail(professor.getEmail());
        return dto;
    }
}

