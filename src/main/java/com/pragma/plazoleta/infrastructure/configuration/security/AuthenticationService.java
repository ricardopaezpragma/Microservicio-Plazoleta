package com.pragma.plazoleta.infrastructure.configuration.security;

import com.pragma.plazoleta.application.dto.AuthRequest;
import com.pragma.plazoleta.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService UserDetailsService;

    public String authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));
        return jwtService.generateToken(UserDetailsService.loadUserByUsername(authRequest.email()));
    }
}
