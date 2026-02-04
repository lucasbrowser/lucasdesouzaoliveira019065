package com.lucas.api_music.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    @PostConstruct
    public void init() {

        if (secret == null || secret.length() < 32) {
            throw new IllegalStateException("JWT secret must be at least 32 chars");
        }

        this.key = Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public String generateToken(String username) {
        return generateToken(username, expiration);
    }

    @Override
    public String generateToken(String username, long expirationSeconds) {

        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
            .signWith(key)
            .compact();
    }

    @Override
    public String generateRefreshToken(String username) {
        long days30 = 60L * 60 * 24 * 30;
        return generateToken(username, days30);
    }

    @Override
    public String extractUser(String token) {
        try {
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {

        String username = extractUser(token);

        return username != null &&
               username.equals(userDetails.getUsername()) &&
               !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        Date exp = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration();

        return exp.before(new Date());
    }
}
