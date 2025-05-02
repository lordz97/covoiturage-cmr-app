package com.blablacar.backend.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final String SECRET = System.getenv("SECRET");

    public String generateToken(UserDetails user) {
        if(SECRET == null){
            throw new IllegalStateException("Variable d'environnement non définie");
        }

        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // Expiration dans 24heures
                .sign(Algorithm.HMAC256(SECRET));
    }
    public String extractUsername(String token) {
        if(SECRET == null){
            throw new IllegalStateException("Variable d'environnement non définie");
        }

        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validate(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) && !isExpired(token);
    }
    private boolean isExpired(String token) {
        if(SECRET == null){
            throw new IllegalStateException("Variable d'environnement non définie");
        }

        Date exp = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getExpiresAt();
        return exp.before(new Date());
    }
}
