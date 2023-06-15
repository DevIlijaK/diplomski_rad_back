package com.diplomski.obavestavanje.nastavnika.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ThesisCommission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    // Constructors, getters, and setters
}
