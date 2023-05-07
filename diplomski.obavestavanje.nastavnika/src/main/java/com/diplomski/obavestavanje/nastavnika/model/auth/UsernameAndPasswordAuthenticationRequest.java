package com.diplomski.obavestavanje.nastavnika.model.auth;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;
}
