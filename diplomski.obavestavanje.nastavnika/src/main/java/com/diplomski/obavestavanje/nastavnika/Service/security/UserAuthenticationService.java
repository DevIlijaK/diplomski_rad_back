package com.diplomski.obavestavanje.nastavnika.Service.security;

import com.diplomski.obavestavanje.nastavnika.Model.responses.LoginResponse;

import java.util.Optional;

public interface UserAuthenticationService {
    /**
     * Logs in with the given {@code credentials}.
     *
     * @param request request
     * @return an {@link Optional} of a user when login succeeds
     */
    Optional<LoginResponse> login(LoginRequest request);

    /**
     * Finds a user by its dao-key.
     *
     * @param token user dao key
     * @return an {@link Optional} of a user when find
     */
    Optional<SystemUserProfile> findByToken(String token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param userId the user to logout
     */
    boolean logout(Long userId);

    /**
     * Replaces old token with new one with extended expire time
     * @param oldToken old token
     * @return new token
     */
    String extendExpireDate(String oldToken);

    /**
     * Checks token
     *
     * @param token jwt token
     * @return true if expired
     */
    boolean isTokenExpired(String token);

    /**
     * Generate new refresh token from the user profile
     *
     * @param profile system user profile
     * @return refresh token
     */
    String generateRefreshToken(SystemUserProfile profile);

    /**
     * Generate new JWT token from the refresh token
     *
     * @param request refresh token request
     * @return new JWT token
     */
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
