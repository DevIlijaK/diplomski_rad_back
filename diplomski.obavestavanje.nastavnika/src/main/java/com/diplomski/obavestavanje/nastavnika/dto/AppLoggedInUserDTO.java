package com.diplomski.obavestavanje.nastavnika.dto;

import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class AppLoggedInUserDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Collection<Role> roles;
    private String accessToken;
    private String refreshToken;
}
