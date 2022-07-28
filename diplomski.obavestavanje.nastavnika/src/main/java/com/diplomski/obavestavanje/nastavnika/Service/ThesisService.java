package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Exception.ResourceNotFoundException;
import com.diplomski.obavestavanje.nastavnika.Model.Professor;
import com.diplomski.obavestavanje.nastavnika.Model.Thesis;
import com.diplomski.obavestavanje.nastavnika.Repository.ProfessorRepository;
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

    @Transactional
    public Thesis saveThesis(Thesis thesis) {
        thesis.getThesisCommission().forEach(commision -> {
            Professor professor =
                    Professor.builder()
                            .professorId(commision.getProfessor().getProfessorId())
                            .fullName(commision.getProfessor().getFullName())
                            .build();
            Professor repositoryProfessor = professorRepository.findByIdentificationNumber(professor.getIdentificationNumber())
                    .orElseGet(() -> professorRepository.save(professor));

        });

        return thesisRepository.save(thesis);
    }
}
