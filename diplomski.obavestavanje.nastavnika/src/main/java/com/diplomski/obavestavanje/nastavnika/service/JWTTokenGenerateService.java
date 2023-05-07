package com.diplomski.obavestavanje.nastavnika.service;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;

public interface JWTTokenGenerateService {
    public String createAccessToken(User appUser, Algorithm algorithm, HttpServletRequest request);
    public String createRefreshToken(User appUser, Algorithm algorithm, HttpServletRequest request);
}
