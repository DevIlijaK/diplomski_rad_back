package com.diplomski.obavestavanje.nastavnika.dto.requests;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class EditAppUserRequest implements java.io.Serializable{
    private static final long serialVersionUID = 260220231836L;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Collection<Role> roles;
}
