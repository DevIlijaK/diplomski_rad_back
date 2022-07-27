package com.diplomski.obavestavanje.nastavnika.Repository;

import com.diplomski.obavestavanje.nastavnika.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findAllByThesisRegistrationDate(Date thesisRegistrationDate);

    List<Thesis> findAllByThesisRegistrationDateBetween(
            Date thesisReqistrationDateStart,
            Date thesisRegistrationDateEnd
    );

}
