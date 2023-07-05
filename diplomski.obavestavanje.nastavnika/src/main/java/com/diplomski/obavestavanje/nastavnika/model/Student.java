package com.diplomski.obavestavanje.nastavnika.model;

import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.AppUser;
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
public class Student extends EmployeeEntity implements Serializable {
    @Column(
            nullable = false
    )
    private String studentId;
    @Column(
            nullable = false
    )
    private String fullName;
    @Column(
            nullable = false
    )

    private String indexNumber;
    @Column(
            nullable = false
    )
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser", referencedColumnName = "email")
    private AppUser appUser;

}
