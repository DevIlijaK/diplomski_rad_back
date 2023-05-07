package com.diplomski.obavestavanje.nastavnika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "professor"
)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
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
