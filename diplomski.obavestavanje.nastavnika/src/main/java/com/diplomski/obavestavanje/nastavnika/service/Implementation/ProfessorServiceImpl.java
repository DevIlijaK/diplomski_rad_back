package com.diplomski.obavestavanje.nastavnika.service.Implementation;

import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.mappers.ProfessorMapper;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.repository.ProfessorRepository;
import com.diplomski.obavestavanje.nastavnika.service.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;

    @Override
    public void saveOrUpdateProfessors(List<Professor> professors) {
        for (Professor professor : professors) {
            Optional<Professor> professorByEmail= professorRepository.findByEmail(professor.getEmail());
            log.info("PRONASAO JE PROFESORA" + professorByEmail);

            if (professorByEmail.isPresent()) {
                Professor existingProfessor = professorByEmail.get();

                boolean hasChanges = checkForChanges(professor, existingProfessor);
                log.info("POSTOJE IZMENE" + hasChanges);

                if (hasChanges) {
                    updateProfessor(existingProfessor, professor);
                    log.info("UPDEJTOVAN PROFESOR" + existingProfessor);
                    professorRepository.save(existingProfessor);
                }
            } else {
                professorRepository.save(professor);
            }
        }
    }

    private boolean checkForChanges(Professor oldProfessor, Professor newProfessor) {
        if (oldProfessor == null || newProfessor == null) {
            log.error("Invalid input: Both oldProfessor and newProfessor must not be null.");
            throw new IllegalArgumentException("Invalid input: Both oldProfessor and newProfessor must not be null.");
        }
        boolean hasChanged = oldProfessor.getProfessorId().equals(newProfessor.getProfessorId());

        if (!oldProfessor.getFullName().equals(newProfessor.getFullName())) {
            hasChanged = true;
        }

        if (!oldProfessor.getIdentificationNumber().equals( newProfessor.getIdentificationNumber())) {
            hasChanged = true;
        }

        log.debug("Changes detected - hasChanged: {}", hasChanged);
        return hasChanged;
    }

    private void updateProfessor(Professor oldProfessor, Professor newProfessor) {
        BeanUtils.copyProperties(newProfessor, oldProfessor, "id", "professorId");
    }


    @Override
    public List<Professor> translateToProfessors(ProfessorDTO[] professorDTOs) {
        List<Professor> professors = new ArrayList<>();

        for (ProfessorDTO professorDTO : professorDTOs) {
            Professor professor = ProfessorMapper.toProfessor(professorDTO);
            professors.add(professor);
        }

        return professors;
    }
}
