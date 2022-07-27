package com.diplomski.obavestavanje.nastavnika.Model;

import javax.persistence.*;

@Entity
public class ThesisProfessorRole {

    @EmbeddedId
    ThesisProfessorRoleKey key;

    @ManyToOne
    @MapsId("thesisId")
    @JoinColumn(name = "thesis_id")
    Thesis thesis;

    @ManyToOne
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    Professor thesisProfessor;

    String thesisProfessorRole;
}
