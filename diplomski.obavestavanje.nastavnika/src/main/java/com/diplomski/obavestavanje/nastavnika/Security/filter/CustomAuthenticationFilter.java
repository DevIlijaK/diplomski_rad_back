package com.diplomski.obavestavanje.nastavnika.Security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.auth.UsernameAndPasswordAuthenticationRequest;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            log.info("Stiglo");
            UsernameAndPasswordAuthenticationRequest authRequest = getAuthRequest(request);
            logAuthRequestDetails(authRequest);
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

    private void logAuthRequestDetails(UsernameAndPasswordAuthenticationRequest authRequest) {
        log.info("Username is: {}", authRequest.getUsername());
        log.info("Password is: {}", authRequest.getPassword());
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(UsernameAndPasswordAuthenticationRequest authRequest) {
        return new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
    }

    private Authentication authenticate(UsernamePasswordAuthenticationToken authenticationToken) {
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User appUser = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey.getSecretKey().toString());
        String accessToken = createAccessToken(appUser, algorithm, request);
        String refreshToken = createRefreshToken(appUser, algorithm, request);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
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
