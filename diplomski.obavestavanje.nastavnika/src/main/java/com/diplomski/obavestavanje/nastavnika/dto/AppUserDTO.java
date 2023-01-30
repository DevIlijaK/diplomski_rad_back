package com.diplomski.obavestavanje.nastavnika.dto;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class AppUserDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Collection<Role> roles;
    private String accessToken;
    private String refreshToken;
}
