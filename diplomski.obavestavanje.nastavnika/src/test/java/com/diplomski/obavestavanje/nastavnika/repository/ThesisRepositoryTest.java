package com.diplomski.obavestavanje.nastavnika.repository;

import com.diplomski.obavestavanje.nastavnika.data.ThesisDataGenerator;
import com.diplomski.obavestavanje.nastavnika.model.Thesis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ThesisRepositoryTest {
    @Autowired
    ThesisRepository thesisRepository;


    @Test
    public void testSaveThesis() {
        List<Thesis> theses = ThesisDataGenerator.generateSampleTheses();
        // Create a sample Thesis
        Thesis thesis = theses.get(0);

        // Save the Thesis to the repository
        Thesis savedThesis = thesisRepository.save(thesis);

        // Retrieve the saved Thesis from the repository
        Optional<Thesis> retrievedThesis = thesisRepository.findById(savedThesis.getId());

        // Assert that the Thesis was saved and retrieved successfully
        assertTrue(retrievedThesis.isPresent());
        assertEquals(thesis.getThesisId(), retrievedThesis.get().getThesisId());
        assertEquals(thesis.getThesisType(), retrievedThesis.get().getThesisType());
        assertEquals(thesis.getThesisTitle(), retrievedThesis.get().getThesisTitle());
        // Add assertions for other attributes

        // Delete the saved Thesis from the repository
        thesisRepository.delete(savedThesis);

        // Verify that the Thesis was deleted
        assertFalse(thesisRepository.findById(savedThesis.getId()).isPresent());
    }

}
