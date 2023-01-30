package com.diplomski.obavestavanje.nastavnika.Security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.auth.UsernameAndPasswordAuthenticationRequest;
import com.diplomski.obavestavanje.nastavnika.Service.Implementation.AppUserServiceImpl;
import com.diplomski.obavestavanje.nastavnika.dto.AppUserDTO;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtSecretKey jwtSecretKey;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AppUserServiceImpl appUserService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authRequest = getAuthRequest(request);
            UsernamePasswordAuthenticationToken authenticationToken = createAuthenticationToken(authRequest);
            return authenticate(authenticationToken);
        } catch (IOException e) {
            log.error("An IO error occurred while trying to authenticate the user: {}", e.getMessage());
            throw new AuthenticationServiceException("An error occurred while trying to authenticate the user", e);
        }
    }

    private UsernameAndPasswordAuthenticationRequest getAuthRequest(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(UsernameAndPasswordAuthenticationRequest authRequest) {
        return new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
    }

    private Authentication authenticate(UsernamePasswordAuthenticationToken authenticationToken) {
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey.getSecretKey().toString());
        String accessToken = createAccessToken(user, algorithm, request);
        String refreshToken = createRefreshToken(user, algorithm, request);
        AppUser appUser = appUserService.getUser(((User)authentication.getPrincipal()).getUsername());
        AppUserDTO appUserDto = AppUserDTO.builder()
                .firstname(appUser.getFirstname())
                .lastname(appUser.getLastname())
                .roles(appUser.getRoles())
                .email(appUser.getEmail())
                .username(appUser.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        Map<String, Object> tokens = new HashMap<>();
        tokens.put("appUser", appUserDto);
        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

    private String createAccessToken(User appUser, Algorithm algorithm, HttpServletRequest request) {
        long accessTokenExpiration = 10 * 60 * 1000;
        return JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    private String createRefreshToken(User appUser, Algorithm algorithm, HttpServletRequest request) {
        long refreshTokenExpiration = 30 * 60 * 1000;
        return JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }
}
