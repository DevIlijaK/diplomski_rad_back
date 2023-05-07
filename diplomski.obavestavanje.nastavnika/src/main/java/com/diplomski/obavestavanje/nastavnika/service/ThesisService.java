package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.model.*;
import com.diplomski.obavestavanje.nastavnika.repository.ProfessorRepository;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisProfessorRoleRepository;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class ThesisService {

    @Autowired
    ThesisRepository thesisRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    ThesisProfessorRoleRepository thesisProfessorRoleRepository;

    @Transactional
    public void saveThesis(JsonModel dataModel) {
        Thesis thesis =
                Thesis.builder()
                        .thesisId(dataModel.getThesisId())
                        .thesisType(dataModel.getThesisType())
                        .thesisTitle(dataModel.getThesisTitle())
                        .thesisRegistrationDate(dataModel.getThesisRegistrationDate())
                        .thesisDateOfSubmission(dataModel.getThesisDateOfSubmission())
                        .thesisDateOfDefense(dataModel.getThesisDateOfDefense())
                        .thesisGrade(dataModel.getThesisGrade())
//                        .thesisTermOfDefense(dataModel.getThesisTermOfDefense())
                        .build();
        thesisRepository.save(thesis);
        dataModel.getThesisCommission().forEach(commision -> {
            Professor professor =
                    Professor.builder()
                            .professorId(commision.getProfessor().getProfessorId())
                            .identificationNumber(commision.getProfessor().getIdentificationNumber())
                            .fullName(commision.getProfessor().getFullName())
                            .build();
            Professor repositoryProfessor = professorRepository.findByIdentificationNumber(professor.getIdentificationNumber())
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
    public List<Thesis> getAllThesis(){
        return thesisRepository.findAll();
    }
}
