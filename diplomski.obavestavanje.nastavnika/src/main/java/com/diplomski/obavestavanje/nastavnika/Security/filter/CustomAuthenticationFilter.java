package com.diplomski.obavestavanje.nastavnika.Security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
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


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /**
         * Iz requesta uzimamo username i password pravimo objekat tipa UsernamePasswordAuthenticationToken
         * i pozivamo authenticationManager da uradi autentifikaciju
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User appUser = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey.getSecretKey().toString());
        String access_token = JWT.create()
                .withSubject(appUser.getUsername()) //nesto posebno u vezi korisnika po emu moze da se identifikuje
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // vreme koliko ce token da traje 10min trenutno (u milisekundama je)
                .withIssuer(request.getRequestURL().toString()) // ko je izdao token
                .withClaim("roles", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(appUser.getUsername()) //nesto posebno u vezi korisnika po emu moze da se identifikuje
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // vreme koliko ce token da traje 10min trenutno (u milisekundama je)
                .withIssuer(request.getRequestURL().toString()) // ko je izdao token
                .sign(algorithm);

        //pravimo mapu koja ce da sadrzi ova dva tokena i onda je upisujemo u body respons-a u json formatu
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);

    }
}
