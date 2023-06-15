package com.diplomski.obavestavanje.nastavnika.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long thesisId;
    private String thesisType;
    private String thesisTitle;
    private Date thesisRegistrationDate;
    private Date thesisDateOfSubmission;
    private Date thesisDateOfDefense;
    private Integer thesisGrade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thesis")
    private List<ThesisCommission> thesisCommission;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;

    @Override
    public String toString() {
        return "Thesis{" +
                "thesisId=" + thesisId +
                ", thesisType='" + thesisType + '\'' +
                ", thesisTitle='" + thesisTitle + '\'' +
                ", thesisRegistrationDate=" + thesisRegistrationDate +
                ", thesisDateOfSubmission=" + thesisDateOfSubmission +
                ", thesisDateOfDefense=" + thesisDateOfDefense +
                ", thesisGrade=" + thesisGrade +
                '}';
    }
}


