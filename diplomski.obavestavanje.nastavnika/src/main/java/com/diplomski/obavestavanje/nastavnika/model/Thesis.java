package com.diplomski.obavestavanje.nastavnika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Thesis implements Serializable {
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
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Thesis{")
                .append("thesisId=").append(thesisId)
                .append(", thesisType='").append(thesisType).append('\'')
                .append(", thesisTitle='").append(thesisTitle).append('\'')
                .append(", thesisRegistrationDate=").append(thesisRegistrationDate)
                .append(", thesisDateOfSubmission=").append(thesisDateOfSubmission)
                .append(", thesisDateOfDefense=").append(thesisDateOfDefense)
                .append(", thesisGrade=").append(thesisGrade)
                .append(", studentId=").append(student != null ? student.getId() : null)
                .append(", thesisCommission=[");

        if (thesisCommission != null && !thesisCommission.isEmpty()) {
            for (ThesisCommission commission : thesisCommission) {
                stringBuilder.append("ID: ").append(commission.getId()).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}


