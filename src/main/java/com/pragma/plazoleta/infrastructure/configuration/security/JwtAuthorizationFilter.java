package com.pragma.plazoleta.infrastructure.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer", "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            if (usernamePAT != null) {
                SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            }else{
                response.setHeader("error","La sesión caducó");
                response.setStatus(HttpStatus.FORBIDDEN.value());

                Map<String,String> error= new HashMap<>();
                error.put("title","La sesión caducó");
                error.put("status","403");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }
        try {
            filterChain.doFilter(request, response);
        }catch (Exception e){
        }

    }
}
