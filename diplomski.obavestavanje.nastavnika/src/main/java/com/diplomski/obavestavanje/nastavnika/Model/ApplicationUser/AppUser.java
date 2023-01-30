package com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstname;
  private String lastname;
  private String username;
  private String password;
  private String email;
  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Role> roles = new ArrayList<>();
}
