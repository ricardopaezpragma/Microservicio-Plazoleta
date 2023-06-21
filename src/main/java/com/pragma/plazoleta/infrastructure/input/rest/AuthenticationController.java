package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.AuthRequest;
import com.pragma.plazoleta.infrastructure.configuration.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping()
    public ResponseEntity authentication(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
}
