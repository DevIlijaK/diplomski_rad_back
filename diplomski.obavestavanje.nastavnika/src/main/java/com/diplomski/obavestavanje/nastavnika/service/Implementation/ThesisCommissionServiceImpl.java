package com.diplomski.obavestavanje.nastavnika.service.Implementation;

import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.mappers.ThesisCommissionMapper;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisCommissionRepository;
import com.diplomski.obavestavanje.nastavnika.service.ThesisCommissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class ThesisCommissionServiceImpl implements ThesisCommissionService {
    private final ThesisCommissionRepository thesisCommissionRepository;
    @Override
    public List<ThesisCommission> translateToThesisCommissions(ThesisCommissionDTO[] thesisCommissionDTOs) {
        List<ThesisCommission> thesisCommissions = new ArrayList<>();

        for (ThesisCommissionDTO thesisCommissionDTO : thesisCommissionDTOs) {
            ThesisCommission thesisCommission = ThesisCommissionMapper.toThesisCommission(thesisCommissionDTO);
            thesisCommissions.add(thesisCommission);
        }

        return thesisCommissions;
    }

}
