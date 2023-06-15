package com.diplomski.obavestavanje.nastavnika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long professorId;

    private String identificationNumber;
    private String fullName;

    // Constructors, getters, and setters
}

