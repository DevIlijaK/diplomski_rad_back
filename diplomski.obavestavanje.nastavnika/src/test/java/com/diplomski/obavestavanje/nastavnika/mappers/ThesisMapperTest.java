package com.diplomski.obavestavanje.nastavnika.mappers;

import com.diplomski.obavestavanje.nastavnika.data.ThesisDataGenerator;
import com.diplomski.obavestavanje.nastavnika.dto.response.ThesisDTO;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringJUnitConfig
@SpringBootTest
public class ThesisMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThesisMapperTest.class);

    @Test
    public void testToThesis() {
        // Generate a random ThesisDTO object
        ThesisDTO thesisDTO = ThesisDataGenerator.generateRandomThesisDTO();

        LOGGER.info("ThesisDTO: {}", thesisDTO);

        // Map the ThesisDTO to a Thesis object using the mapper
        Thesis thesis = ThesisMapper.toThesis(thesisDTO);

        LOGGER.info("Thesis: {}", thesis);

        // Perform assertions or additional logging for verification
        assertNotNull(thesis);
        assertEquals(thesisDTO.getThesisId(), thesis.getThesisId());
        assertEquals(thesisDTO.getThesisType(), thesis.getThesisType());
        assertEquals(thesisDTO.getThesisTitle(), thesis.getThesisTitle());
        assertEquals(thesisDTO.getThesisRegistrationDate(), thesis.getThesisRegistrationDate());
        assertEquals(thesisDTO.getThesisDateOfSubmission(), thesis.getThesisDateOfSubmission());
        assertEquals(thesisDTO.getThesisDateOfDefense(), thesis.getThesisDateOfDefense());
        assertEquals(thesisDTO.getThesisGrade(), thesis.getThesisGrade());
        assertEquals(thesisDTO.getThesisCommission().size(), thesis.getThesisCommission().size());
        assertEquals(thesisDTO.getStudent().getStudentId(), thesis.getStudent().getStudentId());
        assertEquals(thesisDTO.getStudent().getFull_name(), thesis.getStudent().getFullName());
        assertEquals(thesisDTO.getStudent().getIndexNumber(), thesis.getStudent().getIndexNumber());
    }
}

