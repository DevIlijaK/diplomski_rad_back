package com.diplomski.obavestavanje.nastavnika.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties
@Table(
        name = "thesis"
)
@NoArgsConstructor
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long thesisId;
    private String thesisType;
    private String thesisTitle;
    private Date thesisRegistrationDate;
    private Date thesisDateOfSubmission ;
    private Date thesisDateOfDefense;
    private Integer thesisGrade;
    private Time thesisTermOfDefense;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "thesis_student_id",
            referencedColumnName = "Id"
    )
    private Student student;

}
