package com.lucas.api_music.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(String username);

    String generateToken(String username, long expirationSeconds);

    String generateRefreshToken(String username);

    String extractUser(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

}
