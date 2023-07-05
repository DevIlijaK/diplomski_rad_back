package com.diplomski.obavestavanje.nastavnika.repository;

import com.diplomski.obavestavanje.nastavnika.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Optional<Student> findByStudentId(String studentId);
    public Optional<Student> findByEmail(String email);
}
