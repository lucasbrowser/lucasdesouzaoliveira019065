package com.lucas.api_music.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lucas.api_music.exception.BusinessException;
import com.lucas.api_music.model.dto.LoginRequest;
import com.lucas.api_music.model.dto.TokenResponse;
import com.lucas.api_music.security.JwtService;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authManager,
            JwtService jwtService
    ) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest request) {

        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        if (!auth.isAuthenticated()) {
            throw new BusinessException("Credenciais inválidas.");
        }

        String access = jwtService.generateToken(request.getUsername());
        String refresh = jwtService.generateRefreshToken(request.getUsername());

        return new TokenResponse(access, refresh);
    }
}

