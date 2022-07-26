package com.diplomski.obavestavanje.nastavnika.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


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
    private Long thesisId;
    private String thesisType;
    private String thesistitle;
    private Date thesisRegistrationDate;
    private Date thesisDateOfSubmition;
    private Date thesisDateOfDefense;
    private Integer thesisGrade;
    private Time thesisTermOfDefense;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "thesis_professor_map",
            joinColumns = @JoinColumn(
                    name = "thesis_id",
                    referencedColumnName = "thesisId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "professor_id",
                    referencedColumnName = "profesorId"
            )
    )
    private List<Professor> thesisCommision;
    @OneToOne
    @JoinColumn(
            name = "thesis_student_id",
            referencedColumnName = "studentId"
    )
    private Student student;

}
