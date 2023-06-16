package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    void saveOrUpdateStudents(List<Student> studentDTOs);
    List<Student> translateToStudents(StudentDTO[] studentDTOs);
}
