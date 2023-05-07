package com.diplomski.obavestavanje.nastavnika.repository;

import com.diplomski.obavestavanje.nastavnika.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByIdentificationNumber(String identificationNumber);
}
