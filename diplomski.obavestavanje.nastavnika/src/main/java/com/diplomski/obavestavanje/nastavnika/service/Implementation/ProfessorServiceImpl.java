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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;

    @Override
    public void saveOrUpdateProfessors(List<Professor> professors) {
        for (Professor professor : professors) {
            // Check if the professor exists in the repository by professorId
            Optional<Professor> byProfessorId = professorRepository.findByProfessorId(professor.getProfessorId());

            if (byProfessorId.isPresent()) {
                // Professor exists in the repository
                Professor existingProfessor = byProfessorId.get();

                // Check if any changes were made
                boolean hasChanges = checkForChanges(professor, existingProfessor);

                if (hasChanges) {
                    // Update the existing professor with the new values
                    updateProfessor(professor, existingProfessor);
                    professorRepository.save(existingProfessor);
                }
            } else {
                // Professor doesn't exist in the repository
                professorRepository.save(professor);
            }
        }
    }

    private boolean checkForChanges(Professor oldProfessor, Professor newProfessor) {
        if (oldProfessor == null || newProfessor == null) {
            log.error("Invalid input: Both oldProfessor and newProfessor must not be null.");
            throw new IllegalArgumentException("Invalid input: Both oldProfessor and newProfessor must not be null.");
        }
        boolean hasChanged = !Objects.equals(oldProfessor.getProfessorId(), newProfessor.getProfessorId());

        if (!Objects.equals(oldProfessor.getFullName(), newProfessor.getFullName())) {
            hasChanged = true;
        }

        if (!Objects.equals(oldProfessor.getIdentificationNumber(), newProfessor.getIdentificationNumber())) {
            hasChanged = true;
        }

        log.debug("Changes detected - hasChanged: {}", hasChanged);
        return hasChanged;
    }

    private void updateProfessor(Professor oldProfessor, Professor newProfessor) {
        BeanUtils.copyProperties(newProfessor, oldProfessor, "professorId");
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
