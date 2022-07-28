package com.diplomski.obavestavanje.nastavnika.Repository;

import com.diplomski.obavestavanje.nastavnika.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByIdentificationNumber(String identificationNumber);
}
