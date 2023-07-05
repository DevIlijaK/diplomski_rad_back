package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface ThesisService {
    void setThesisWithCommissionAndStudent(List<ThesisDTO> thesisDTO);

    List<Thesis> returnAllByThesisDateOfDefenseBetween(Date startDate, Date endDate);

    List<Thesis> getAllThesis();

    List<Thesis> filterDuplicates(List<Thesis> theses);

    List<ThesisDTO> findThesesByProfessorAndDateRange(String email,
                                                      LocalDateTime startDate,
                                                      LocalDateTime endDate);


}
