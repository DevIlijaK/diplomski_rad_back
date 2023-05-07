package com.diplomski.obavestavanje.nastavnika.Repository;

import com.diplomski.obavestavanje.nastavnika.repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThesisRepositoryIntegrationTest {
    @Autowired
    ThesisRepository thesisRepository;


//    @Test
//    List<Thesis> findAllByThesisDateOfDefenseBetweenTest () {
//        Thesis thesis = Thesis.builder()
//                .thesisId
//                .thesisType
//                .thesisTitle
//                .thesisRegistrationDate
//                .thesisDateOfSubmission
//                .thesisDateOfDefense
//                .thesisGrade
//                .thesisTermOfDefense
//
//                .build();
//        Date startDate = new Date(2022-11-22);
//        Date endDate = new Date(2022-11-22);

//        return thesisRepository.findAllByThesisDateOfDefenseBetween(startDate, endDate);
//    }

}
