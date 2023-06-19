package com.diplomski.obavestavanje.nastavnika.service.Implementation;

import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.mappers.ThesisMapper;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;
import com.diplomski.obavestavanje.nastavnika.repository.ProfessorRepository;
import com.diplomski.obavestavanje.nastavnika.repository.StudentRepository;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisRepository;
import com.diplomski.obavestavanje.nastavnika.service.ThesisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ThesisServiceImpl implements ThesisService {

    private final ThesisRepository thesisRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;


    public List<Thesis> returnAllByThesisDateOfDefenseBetween(Date startDate, Date endDate) {
        return thesisRepository.findAllByThesisDateOfDefenseBetween(startDate, endDate);
    }

    public List<Thesis> setThesisWithCommissionAndStudent(List<ThesisDTO> thesisDTOList) {
        List<Thesis> theses = new ArrayList<>();

        for (ThesisDTO thesisDTO : thesisDTOList) {
            Thesis thesis = ThesisMapper.toThesis(thesisDTO);
            String studentId = thesisDTO.getStudent().getStudentId();
            Student student = studentRepository.findByStudentId(studentId)
                    .orElseThrow(() -> {
                        String errorMessage = "Student not found with ID: " + studentId;
                        log.error(errorMessage);
                        return new RuntimeException(errorMessage);
                    });
            thesis.setStudent(student);

            List<ThesisCommission> thesisCommissions = thesisDTO.getThesisCommission().stream()
                    .map(thesisCommissionDTO -> {
                        String professorId = thesisCommissionDTO.getProfessor().getProfessorId();
                        Professor professor = professorRepository.findByProfessorId(professorId)
                                .orElseThrow(() -> {
                                    String errorMessage = "Professor not found with ID: " + professorId;
                                    log.error(errorMessage);
                                    return new RuntimeException(errorMessage);
                                });

                        ThesisCommission thesisCommission = new ThesisCommission();
                        thesisCommission.setProfessor(professor);
                        thesisCommission.setRole(thesisCommissionDTO.getRole());
                        thesisCommission.setThesis(thesis);
                        return thesisCommission;
                    })
                    .collect(Collectors.toList());

            thesis.setThesisCommission(thesisCommissions);
            theses.add(thesis);
        }

        return theses;
    }


    public List<Thesis> getAllThesis() {
        return thesisRepository.findAll();
    }

    public List<Thesis> filterDuplicates(List<Thesis> theses) {
        return theses.stream()
                .map(this::updateIfChanged)
                .collect(Collectors.toList());
    }

    @Override
    public List<ThesisDTO> findThesesByProfessorAndDateRange(String email, Date startDate, Date endDate) {
        log.info("EMAIL: " + email);
        log.info("START DATE: " + startDate);
        log.info("END DATE: " + endDate);
        List<Thesis> thesesByProfessorAndDateRange = thesisRepository.findThesesByProfessorAndDateRange(
                email, startDate, endDate);
        log.info("THESIS: " + thesesByProfessorAndDateRange);
        return thesesByProfessorAndDateRange.stream()
                .map(ThesisMapper::toThesisDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Thesis updateIfChanged(Thesis thesis) {
        Thesis existingThesis = thesisRepository.findByThesisId(thesis.getThesisId());

        if (existingThesis != null) {
            boolean hasChanged = false;
            try {
                Field[] fields = Thesis.class.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object existingValue = field.get(existingThesis);
                    Object newValue = field.get(thesis);

                    if (!Objects.equals(existingValue, newValue)) {
                        field.set(existingThesis, newValue);
                        hasChanged = true;
                    }
                }
            } catch (IllegalAccessException e) {
                System.err.println("Error accessing fields through reflection: " + e.getMessage());
                throw new RuntimeException("Failed to update thesis due to reflection error");
            }

            if (hasChanged) {
                return thesisRepository.saveAndFlush(existingThesis);
            }
        }
        return thesisRepository.save(thesis);
    }


}
