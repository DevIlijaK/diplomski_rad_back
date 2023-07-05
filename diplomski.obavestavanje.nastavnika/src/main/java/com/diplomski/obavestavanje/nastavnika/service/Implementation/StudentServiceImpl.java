package com.diplomski.obavestavanje.nastavnika.service.Implementation;

import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.mappers.StudentMapper;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import com.diplomski.obavestavanje.nastavnika.repository.StudentRepository;
import com.diplomski.obavestavanje.nastavnika.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void saveOrUpdateStudents(List<Student> students) {
        for (Student student : students) {
            Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());

            if (studentByEmail.isPresent()) {
                log.info("Nasao je studenta" + studentByEmail);
                Student existingStudent = studentByEmail.get();

                boolean hasChanges = checkForChanges(student, existingStudent);
                log.info("Pronasao je promene" + hasChanges);

                if (hasChanges) {
                    updateStudent(existingStudent, student);
                    log.info("updejtova je studenta" + student);
                    studentRepository.save(existingStudent);
                    log.info("updejtova je studenta");
                }
            } else {
                studentRepository.save(student);
            }
        }
    }

    private boolean checkForChanges(Student oldStudent, Student newStudent) {
        if (oldStudent == null || newStudent == null) {
            log.error("Invalid input: Both oldStudent and newStudent must not be null.");
            throw new IllegalArgumentException("Invalid input: Both oldStudent and newStudent must not be null.");
        }
        boolean hasChanged = oldStudent.getStudentId().equals(newStudent.getStudentId());

        if (!oldStudent.getFullName().equals(newStudent.getFullName())) {
            hasChanged = true;
        }

        if (oldStudent.getIndexNumber().equals(newStudent.getIndexNumber())) {
            hasChanged = true;
        }

        log.debug("Changes detected - hasChanged: {}", hasChanged);
        return hasChanged;
    }

    private void updateStudent(Student olsStudent, Student newStudent) {
        BeanUtils.copyProperties(newStudent, olsStudent, "id", "studentId");
    }

    @Override
    public List<Student> translateToStudents(StudentDTO[] studentDTOs) {
        List<Student> students = new ArrayList<>();

        for (StudentDTO studentDTO : studentDTOs) {
            Student student = StudentMapper.toStudent(studentDTO);
            students.add(student);
        }

        return students;
    }

}
