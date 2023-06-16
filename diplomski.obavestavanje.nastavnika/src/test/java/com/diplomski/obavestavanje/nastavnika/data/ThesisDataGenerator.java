package com.diplomski.obavestavanje.nastavnika.data;

import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThesisDataGenerator {
    public static ThesisDTO generateRandomThesisDTO() {
        Random random = new Random();


        return ThesisDTO.builder()
                .thesisId(random.nextLong())
                .thesisType("Type " + random.nextInt(10))
                .thesisTitle("Title " + random.nextInt(10))
                .thesisRegistrationDate(new Date(System.currentTimeMillis()))
                .thesisDateOfSubmission(new Date(System.currentTimeMillis()))
                .thesisDateOfDefense(new Date(System.currentTimeMillis()))
                .thesisGrade(random.nextInt(101))
                .thesisCommission(generateRandomThesisCommissionDTOs())
                .student(generateRandomStudentDTO())
                .build();
    }

    private static List<ThesisCommissionDTO> generateRandomThesisCommissionDTOs() {
        Random random = new Random();
        List<ThesisCommissionDTO> thesisCommissionDTOs = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ProfessorDTO professorDTO = ProfessorDTO.builder()
                    .professorId(random.nextLong())
                    .identificationNumber("ID-" + random.nextInt(1000))
                    .fullName("Professor " + random.nextInt(10))
                    .build();

            ThesisCommissionDTO thesisCommissionDTO = ThesisCommissionDTO.builder()
                    .professor(professorDTO)
                    .role("Role " + random.nextInt(10))
                    .build();

            thesisCommissionDTOs.add(thesisCommissionDTO);
        }

        return thesisCommissionDTOs;
    }

    private static StudentDTO generateRandomStudentDTO() {
        Random random = new Random();

        // Generate a random StudentDTO object

        return StudentDTO.builder()
                .studentId(random.nextLong())
                .full_name("Student " + random.nextInt(10))
                .indexNumber("Index " + random.nextInt(1000))
                .build();
    }

    public static List<Thesis> generateSampleTheses() {
        Student student = Student.builder()
                .studentId(1L)
                .fullName("John Doe")
                .indexNumber("123456")
                .build();

        Professor professor = Professor.builder()
                .professorId(1L)
                .identificationNumber("12345")
                .fullName("John Doe")
                .build();

        ThesisCommission thesisCommission = ThesisCommission.builder()
                .role("Chair")
                .professor(professor)
                .build();

        List<Thesis> theses = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            Thesis thesis = Thesis.builder()
                    .id((long) (i + 1))
                    .thesisId((long) (100 + i))
                    .thesisType("Type " + (i + 1))
                    .thesisTitle("Title " + (i + 1))
                    .thesisRegistrationDate(new Date(System.currentTimeMillis()))
                    .thesisDateOfSubmission(new Date(System.currentTimeMillis()))
                    .thesisDateOfDefense(new Date(System.currentTimeMillis()))
                    .thesisGrade(random.nextInt(101))
                    .thesisCommission(List.of(thesisCommission))
                    .student(student)
                    .build();

            theses.add(thesis);
        }

        return theses;
    }
}