package com.diplomski.obavestavanje.nastavnika.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties
@AllArgsConstructor
@Table(
        name = "student"
)
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
