package com.lucas.api_music.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String user, long expirationSeconds) {

        SecretKey key = Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
            .subject(user) // em vez de setSubject
            .issuedAt(new Date()) // em vez de setIssuedAt
            .expiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
            .signWith(key)
            .compact();
    }

    public String getUser(String token) {

    SecretKey key = Keys.hmacShaKeyFor(
        secret.getBytes(StandardCharsets.UTF_8)
    );

    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }
}
