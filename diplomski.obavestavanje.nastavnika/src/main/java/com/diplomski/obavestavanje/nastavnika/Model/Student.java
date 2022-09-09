package com.diplomski.obavestavanje.nastavnika.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties
@AllArgsConstructor
@ToString
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
    private String full_name;
    @Column(
            nullable = false
    )
    private String indexNumber;


}
