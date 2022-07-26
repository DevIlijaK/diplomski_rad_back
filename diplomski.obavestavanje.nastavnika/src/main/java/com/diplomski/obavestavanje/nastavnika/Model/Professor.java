package com.diplomski.obavestavanje.nastavnika.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long profesorId;
    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;
    @Column(
            name = "role",
            nullable = false
    )
    private String role;




}
