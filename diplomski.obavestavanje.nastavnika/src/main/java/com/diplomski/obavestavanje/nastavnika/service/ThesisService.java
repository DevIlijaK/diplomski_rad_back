package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.model.JsonModel;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ThesisService {
    List<Thesis> setThesisWithCommissionAndStudent(List<ThesisDTO> thesisDTO);

    List<Thesis> returnAllByThesisDateOfDefenseBetween(Date startDate, Date endDate);

    List<Thesis> getAllThesis();

    List<Thesis> filterDuplicates(List<Thesis> theses);

    List<Thesis> findThesesByProfessorAndDateRange(String email,
                                                   Date startDate,
                                                   Date endDate);


}
