package com.diplomski.obavestavanje.nastavnika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private String studentId;
    @Column(
            nullable = false
    )
    private String fullName;
    @Column(
            nullable = false
    )
    private String indexNumber;
    private String email;

}
