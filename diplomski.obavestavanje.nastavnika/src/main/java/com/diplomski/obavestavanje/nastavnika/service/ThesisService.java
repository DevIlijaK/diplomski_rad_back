package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.model.JsonModel;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;

import java.sql.Date;
import java.util.List;

public interface ThesisService {
    Thesis saveThesis(Thesis thesis);
    List<Thesis> returnAllByThesisDateOfDefenseBetween(Date startDate, Date endDate);
    List<Thesis> getAllThesis();
    List<Thesis> filterDuplicates(List<Thesis> theses);


}
