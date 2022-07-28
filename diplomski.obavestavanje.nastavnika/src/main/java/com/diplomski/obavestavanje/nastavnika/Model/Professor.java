package com.diplomski.obavestavanje.nastavnika.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@JsonIgnoreProperties
@AllArgsConstructor
@Table(
        name = "professor"
)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "professorId",
            nullable = false
    )
    private Long professorId;
    @Column(
            name = "identification_number",
            nullable = false
    )
    private String identificationNumber;
    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;

}
