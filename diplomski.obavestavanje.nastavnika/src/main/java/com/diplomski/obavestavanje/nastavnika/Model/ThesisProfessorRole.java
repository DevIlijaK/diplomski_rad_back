package com.diplomski.obavestavanje.nastavnika.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThesisProfessorRole {

    @EmbeddedId
    ThesisProfessorRoleKey key;

    @ManyToOne
    @MapsId("thesisId")
    @JoinColumn(name = "thesis_id")
    Thesis thesis;

    @ManyToOne(
    )
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    Professor professor;

    String thesisProfessorRole;
}
