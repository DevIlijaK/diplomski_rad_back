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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void saveOrUpdateStudents(List<Student> students) {
        for (Student student : students) {
            // Check if the student exists in the repository by studentId
            Optional<Student> byStudentId = studentRepository.findByStudentId(student.getStudentId());

            if (byStudentId.isPresent()) {
                // Student exists in the repository
                Student existingStudent = byStudentId.get();

                // Check if any changes were made
                boolean hasChanges = checkForChanges(student, existingStudent);

                if (hasChanges) {
                    // Update the existing student with the new values
                    updateStudent(student, existingStudent);
                    studentRepository.save(existingStudent);
                }
            } else {
                // Student doesn't exist in the repository
                studentRepository.save(student);
            }
        }
    }

    private boolean checkForChanges(Student oldStudent, Student newStudent) {
        if (oldStudent == null || newStudent == null) {
            log.error("Invalid input: Both oldStudent and newStudent must not be null.");
            throw new IllegalArgumentException("Invalid input: Both oldStudent and newStudent must not be null.");
        }
        boolean hasChanged = !Objects.equals(oldStudent.getStudentId(), newStudent.getStudentId());

        if (!Objects.equals(oldStudent.getFullName(), newStudent.getFullName())) {
            hasChanged = true;
        }

        if (!Objects.equals(oldStudent.getIndexNumber(), newStudent.getIndexNumber())) {
            hasChanged = true;
        }

        log.debug("Changes detected - hasChanged: {}", hasChanged);
        return hasChanged;
    }

    private void updateStudent(Student olsStudent, Student newStudent) {
        BeanUtils.copyProperties(newStudent, olsStudent, "studentId");
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
