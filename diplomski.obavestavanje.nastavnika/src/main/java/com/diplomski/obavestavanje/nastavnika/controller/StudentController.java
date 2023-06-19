package com.diplomski.obavestavanje.nastavnika.controller;


import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import com.diplomski.obavestavanje.nastavnika.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class StudentController {

    private static final String JSON_URL = "http://localhost:3001/students";

    private final RestTemplate restTemplate;
    private final StudentService studentService;


    @GetMapping("/students")
//    @Scheduled(cron = "*/10 * * * * *")
    public boolean fetchAllStudents() {
        // Perform GET request to fetch all students
        StudentDTO[] studentsDTO = restTemplate.getForObject(JSON_URL, StudentDTO[].class);
        List<Student> students = studentService.translateToStudents(studentsDTO);
        studentService.saveOrUpdateStudents(students);
        return true;
    }
}
