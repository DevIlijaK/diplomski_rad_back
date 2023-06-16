package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.data.ThesisDataGenerator;
import com.diplomski.obavestavanje.nastavnika.dto.response.ProfessorDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.StudentDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisCommissionDTO;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.model.Professor;
import com.diplomski.obavestavanje.nastavnika.model.Student;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import com.diplomski.obavestavanje.nastavnika.model.ThesisCommission;
import com.diplomski.obavestavanje.nastavnika.repository.ThesisRepository;
import com.diplomski.obavestavanje.nastavnika.service.ThesisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
class ThesisServiceTest {

//    private ThesisRepository thesisRepository;

    private final ThesisService thesisService;

    @Autowired
    public ThesisServiceTest(ThesisService thesisService) {
        this.thesisService = thesisService;
    }


    @Test
    public void testFilterDuplicates() {
        List<Thesis> theses = ThesisDataGenerator.generateSampleTheses();


        log.info("Original Theses: {}", theses);

        // Filter out the duplicates
        List<Thesis> filteredTheses = thesisService.filterDuplicates(theses);

        log.info("Filtered Theses: {}", filteredTheses);

        // Assert that the filtered list does not contain any duplicates
        assertEquals(3, filteredTheses.size());
    }

}
