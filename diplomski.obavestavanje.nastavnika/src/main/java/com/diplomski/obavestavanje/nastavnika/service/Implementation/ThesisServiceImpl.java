package com.diplomski.obavestavanje.nastavnika.service.Implementation;

import com.diplomski.obavestavanje.nastavnika.model.Student;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import com.diplomski.obavestavanje.nastavnika.repository.ProfessorRepository;
import com.diplomski.obavestavanje.nastavnika.repository.StudentRepository;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisProfessorRoleRepository;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisRepository;
import com.diplomski.obavestavanje.nastavnika.service.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    private final ThesisRepository thesisRepository;
    private final ProfessorRepository professorRepository;
    private final ThesisProfessorRoleRepository thesisProfessorRoleRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Thesis saveThesis(Thesis thesis) {

        return thesisRepository.save(thesis);
//        thesis.getThesisCommission().forEach(commision -> {
//            Professor professor =
//                    Professor.builder()
//                            .professorId(commision.getProfessor().getProfessorId())
//                            .identificationNumber(commision.getProfessor().getIdentificationNumber())
//                            .fullName(commision.getProfessor().getFullName())
//                            .build();
//            Professor repositoryProfessor = professorRepository.findByIdentificationNumber(professor.getIdentificationNumber())
//                    .orElseGet(() -> professorRepository.save(professor));
//            ThesisProfessorRole thesisProfessorRole =
//                    ThesisProfessorRole.builder()
//                            .professor(repositoryProfessor)
//                            .thesis(thesis)
//                            .thesisProfessorRole(commision.getThesisProfessorRole())
//                            .key(new ThesisProfessorRoleKey(thesis.getThesisId(), repositoryProfessor.getProfessorId() ))
//                            .build();
//
//            thesisProfessorRoleRepository.save(thesisProfessorRole);
//            System.out.println("proslo");
//        });
    }
    public List<Thesis> returnAllByThesisDateOfDefenseBetween(Date startDate, Date endDate){
        return thesisRepository.findAllByThesisDateOfDefenseBetween(startDate, endDate);
    }
    public List<Thesis> getAllThesis(){
        return thesisRepository.findAll();
    }

    public List<Thesis> filterDuplicates(List<Thesis> theses) {
        return theses.stream()
                .map(this::updateIfChanged)
                .collect(Collectors.toList());
    }

    private Thesis updateIfChanged(Thesis thesis) {
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
                // Handle the reflection exception appropriately
                // For example, log the exception and throw a custom exception
                System.err.println("Error accessing fields through reflection: " + e.getMessage());
                throw new RuntimeException("Failed to update thesis due to reflection error");
            }

            if (hasChanged) {
                return thesisRepository.saveAndFlush(existingThesis);
            }
        }
//        Student existingStudent = studentRepository.findByStudentId(thesis.getStudent().getStudentId());
//        if (existingStudent != null) {
//            student = entityManager.merge(existingStudent);
//        }

        return thesisRepository.saveAndFlush(thesis);
    }


}
