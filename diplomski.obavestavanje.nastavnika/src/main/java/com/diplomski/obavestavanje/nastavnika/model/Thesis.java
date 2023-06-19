package com.diplomski.obavestavanje.nastavnika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "thesis")
    private List<ThesisCommission> thesisCommission;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
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


