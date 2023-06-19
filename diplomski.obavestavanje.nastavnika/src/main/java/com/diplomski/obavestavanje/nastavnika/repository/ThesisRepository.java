package com.diplomski.obavestavanje.nastavnika.repository;

import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findAllByThesisRegistrationDate(Date thesisRegistrationDate);
    Thesis findByThesisId(Long thesisId);

    List<Thesis> findAllByThesisDateOfDefenseBetween(
            Date thesisReqistrationDateStart,
            Date thesisRegistrationDateEnd
    );

    @Query(value = "SELECT t.*, p.*, s.* " +
            "FROM thesis t " +
            "JOIN thesis_commission tc ON tc.thesis_id = t.id " +
            "JOIN professor p ON p.professor_id = tc.professor_id " +
            "JOIN student s ON s.student_id = t.student_id " +
            "WHERE p.email = :email " +
            "AND t.thesis_date_of_defense BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Thesis> findThesesByProfessorAndDateRange(
            @Param("email") String email,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


}
