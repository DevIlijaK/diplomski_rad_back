package com.diplomski.obavestavanje.nastavnika.dto.requests;

import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditAppUserRequest implements java.io.Serializable{
    private static final long serialVersionUID = 260220231836L;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Collection<Role> roles;
}
