package br.com.holytickets.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class JWTUtils {
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String email, String role) {
        return JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withExpiresAt(expirationDate())
                .sign(Algorithm.HMAC256(secretKey));
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(5).atZone(ZoneId.systemDefault()).toInstant();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
