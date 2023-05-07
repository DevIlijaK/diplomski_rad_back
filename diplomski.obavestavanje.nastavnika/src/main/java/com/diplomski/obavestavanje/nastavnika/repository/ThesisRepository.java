package com.diplomski.obavestavanje.nastavnika.repository;

import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findAllByThesisRegistrationDate(Date thesisRegistrationDate);

    List<Thesis> findAllByThesisDateOfDefenseBetween(
            Date thesisReqistrationDateStart,
            Date thesisRegistrationDateEnd
    );

}
