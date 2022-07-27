package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Model.Thesis;
import com.diplomski.obavestavanje.nastavnika.Repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisService {

    @Autowired
    ThesisRepository thesisRepository;

    public Thesis saveThesis(Thesis thesis){
        return thesisRepository.save(thesis);
    }
}
