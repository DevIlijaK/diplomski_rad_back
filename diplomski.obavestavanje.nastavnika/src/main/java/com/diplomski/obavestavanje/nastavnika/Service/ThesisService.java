package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Exception.ResourceNotFoundException;
import com.diplomski.obavestavanje.nastavnika.Model.*;
import com.diplomski.obavestavanje.nastavnika.Repository.ProfessorRepository;
import com.diplomski.obavestavanje.nastavnika.Repository.ThesisProfessorRoleRepository;
import com.diplomski.obavestavanje.nastavnika.Repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ThesisService {

    @Autowired
    ThesisRepository thesisRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    ThesisProfessorRoleRepository thesisProfessorRoleRepository;

    @Transactional
    public void saveThesis(JsonModel object) {
       Thesis thesis = Thesis.builder()
               .thesisId(object.getThesisId())
               .thesisType(object.getThesisType())
               .thesisTitle(object.getThesisTitle())
               .thesisRegistrationDate(object.getThesisRegistrationDate())
               .thesisDateOfSubmission(object.getThesisDateOfSubmission())
               .thesisDateOfDefense(object.getThesisDateOfDefense())
               .thesisGrade(object.getThesisGrade())
               .thesisTermOfDefense(object.getThesisTermOfDefense())
               .student(object.getStudent())
               .build();
       thesisRepository.save(thesis);
        if(object.getThesisCommission() != null)
            object.getThesisCommission().forEach(commision -> {
            Professor professor =
                    Professor.builder()
                            .professorId(commision.getProfessor().getProfessorId())
                            .fullName(commision.getProfessor().getFullName())
                            .build();
            Professor repositoryProfessor = professorRepository.findByProfessorId(professor.getProfessorId())
                    .orElseGet(() -> professorRepository.save(professor));
            ThesisProfessorRole thesisProfessorRole =
                    ThesisProfessorRole.builder()
                            .professor(repositoryProfessor)
                            .thesis(thesis)
                            .thesisProfessorRole(commision.getThesisProfessorRole())
                            .key(new ThesisProfessorRoleKey(thesis.getThesisId(), repositoryProfessor.getProfessorId() ))
                            .build();

            thesisProfessorRoleRepository.save(thesisProfessorRole);
            System.out.println("proslo");
        });
    }
    public List<Thesis>returnAllByThesisDateOfDefenseBetween(Date startDate, Date endDate){
        return thesisRepository.findAllByThesisDateOfDefenseBetween(startDate, endDate);
    }
}
