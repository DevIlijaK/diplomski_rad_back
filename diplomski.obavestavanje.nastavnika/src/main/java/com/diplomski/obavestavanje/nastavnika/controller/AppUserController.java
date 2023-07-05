package com.diplomski.obavestavanje.nastavnika.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.diplomski.obavestavanje.nastavnika.constants.UserRoles;
import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.service.AppUserService;
import com.diplomski.obavestavanje.nastavnika.dto.requests.EditAppUserRequest;
import com.diplomski.obavestavanje.nastavnika.dto.requests.GetAppUsersRequest;
import com.diplomski.obavestavanje.nastavnika.dto.response.AppUsersSearchResponse;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;
    private final JwtSecretKey jwtSecretKey;

    @PostMapping("/get-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUsersSearchResponse> getUsers(@RequestBody GetAppUsersRequest getAppUsersRequest) {
        return ResponseEntity.ok().body(appUserService.getUsers(getAppUsersRequest));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> saveUsers(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body("OK");
    }

    @PostMapping("/role/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/add-to-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // The filter will handle the authentication process and return the token
    }

    @PostMapping("/token/refresh")
    public void refreshToken(@RequestBody String refreshToken, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Refresh token" + refreshToken);
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            try {
                String refresh_token = refreshToken.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey.getSecretKey().toString());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(appUser.getUsername()) //nesto posebno u vezi korisnika po emu moze da se identifikuje
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // vreme koliko ce token da traje 10min trenutno (u milisekundama je)
                        .withIssuer(request.getRequestURL().toString()) // ko je izdao token
                        .withClaim("roles", appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                /**
                 * Mozda ubacim ovde da se generise i novi refresh token...
                 */
                //pravimo mapu koja ce da sadrzi ova dva tokena i onda je upisujemo u body respons-a u json formatu
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                error.put("token_type", "Refresh Token");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("get-roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Role>> getAppUserRoles() {
        return ResponseEntity.ok().body(appUserService.getAppUserRoles());
    }

    @PutMapping("/update-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody EditAppUserRequest editAppUserRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("message", appUserService.updateAppUser(editAppUserRequest));
        return ResponseEntity.ok().body(response);
    }

    @Data
    static
    class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
