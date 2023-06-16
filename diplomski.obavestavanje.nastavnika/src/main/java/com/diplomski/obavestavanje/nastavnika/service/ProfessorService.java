package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfessorService {
    void saveOrUpdateProfessors(List<Professor> professorDTOs);
    public List<Professor> translateToProfessors(ProfessorDTO[] professorDTOs);
}
