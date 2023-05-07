package com.diplomski.obavestavanje.nastavnika.service.Implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.diplomski.obavestavanje.nastavnika.service.JWTTokenGenerateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class JWTTokenGenerateServiceImpl implements JWTTokenGenerateService {
    public String createAccessToken(User appUser, Algorithm algorithm, HttpServletRequest request) {
        long accessTokenExpiration = 30 * 60 * 1000;
        return JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String createRefreshToken(User appUser, Algorithm algorithm, HttpServletRequest request) {
        long refreshTokenExpiration = 30 * 60 * 1000;
        return JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }
}
