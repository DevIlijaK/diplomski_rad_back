package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Exception.ResourceNotFoundException;
import com.diplomski.obavestavanje.nastavnika.Model.Professor;
import com.diplomski.obavestavanje.nastavnika.Model.Thesis;
import com.diplomski.obavestavanje.nastavnika.Model.ThesisProfessorRole;
import com.diplomski.obavestavanje.nastavnika.Model.ThesisProfessorRoleKey;
import com.diplomski.obavestavanje.nastavnika.Repository.ProfessorRepository;
import com.diplomski.obavestavanje.nastavnika.Repository.ThesisProfessorRoleRepository;
import com.diplomski.obavestavanje.nastavnika.Repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void saveThesis(Thesis thesis) {
        System.out.println(thesis);
        thesisRepository.save(thesis);
        thesis.getThesisCommission().forEach(commision -> {
            Professor professor =
                    Professor.builder()
                            .professorId(commision.getProfessor().getProfessorId())
                            .identificationNumber(commision.getProfessor().getIdentificationNumber())
                            .fullName(commision.getProfessor().getFullName())
                            .build();
            System.out.println(professor);
            Professor repositoryProfessor = professorRepository.findByIdentificationNumber(professor.getIdentificationNumber())
                    .orElseGet(() -> professorRepository.save(professor));
            ThesisProfessorRole thesisProfessorRole =
                    ThesisProfessorRole.builder()
                            .professor(repositoryProfessor)
                            .thesis(thesis)
                            .thesisProfessorRole(commision.getThesisProfessorRole())
                            .key(new ThesisProfessorRoleKey(thesis.getThesisId(), repositoryProfessor.getProfessorId() ))
                            .build();
            System.out.println(thesisProfessorRole);
            thesisProfessorRoleRepository.save(thesisProfessorRole);

        });

    }
}
