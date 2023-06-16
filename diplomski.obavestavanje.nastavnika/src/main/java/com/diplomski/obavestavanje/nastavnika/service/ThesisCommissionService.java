package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;

import java.util.List;

public interface ThesisCommissionService {
    public List<ThesisCommission> translateToThesisCommissions(ThesisCommissionDTO[] thesisCommissionDTOs);
}
