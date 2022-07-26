package com.diplomski.obavestavanje.nastavnika.Repository;

import com.diplomski.obavestavanje.nastavnika.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Professor, Long> {
//    Profesor findByEmailProfesora(String email);
//
//    @Query(
//            value = "select * from profesor where email_profesora=:email123",
//            nativeQuery = true
//    )
//    Profesor vratiProfesoraPoEmialu(@Param("email123") String email);
}