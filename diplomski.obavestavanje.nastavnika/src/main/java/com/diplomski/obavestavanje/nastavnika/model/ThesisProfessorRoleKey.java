package com.diplomski.obavestavanje.nastavnika.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode
public class ThesisProfessorRoleKey implements Serializable {

    @Column(name = "thesis_id")
    String thesisId;

    @Column(name = "professor_id")
    Long professorId;

}
