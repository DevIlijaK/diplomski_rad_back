package com.diplomski.obavestavanje.nastavnika.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private Long studentId;
    @Column(
            nullable = false
    )
    private String fullName;
    @Column(
            nullable = false
    )
    private String indexNumber;


}
