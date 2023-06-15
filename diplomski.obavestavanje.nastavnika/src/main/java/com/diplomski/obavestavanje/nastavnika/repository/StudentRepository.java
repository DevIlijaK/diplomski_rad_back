package com.diplomski.obavestavanje.nastavnika.repository;

import com.diplomski.obavestavanje.nastavnika.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Student findByStudentId(Long studentId);
}
