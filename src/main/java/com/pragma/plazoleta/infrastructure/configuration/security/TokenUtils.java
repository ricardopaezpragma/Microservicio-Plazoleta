package com.pragma.plazoleta.infrastructure.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class TokenUtils {
    private static final String ACCESS_TOKEN_SECRET = "RicardoPaezPragma/MicroservicioPlazoleta2023";
    private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2629743L;

    public static String createToken(int id,String name, String lastName, String email, String authorities) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("id", id);
        extra.put("name", name);
        extra.put("lastName", lastName);
        extra.put("authorities", authorities);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            String role = "Vacio";
            role = (String) claims.get("authorities");
            return new UsernamePasswordAuthenticationToken((Object) email, null, Collections.singleton(new SimpleGrantedAuthority(role)));
        } catch (JwtException e) {
            return null;
        }

    }
}
