package com.diplomski.obavestavanje.nastavnika.mappers;

import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.model.Student;

import java.util.ArrayList;
import java.util.List;
public class StudentMapper {
    public static Student toStudent(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getStudentId());
        student.setStudentId(dto.getStudentId());
        student.setFullName(dto.getFull_name());
        student.setIndexNumber(dto.getIndexNumber());
        return student;
    }

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getId());
        dto.setFull_name(student.getFullName());
        dto.setIndexNumber(student.getIndexNumber());
        return dto;
    }

    public static List<StudentDTO> toStudentDTOList(List<Student> students) {
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(toStudentDTO(student));
        }
        return dtos;
    }
}

