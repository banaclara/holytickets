package br.com.holytickets.security;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import br.com.holytickets.models.Establishment;
import br.com.holytickets.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTAuthFilter extends OncePerRequestFilter {
    private static final String secretKey = "secret_key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null && validateToken(token)) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private String generateUserToken(User user) {
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role", "USER")
                .withExpiresAt(expirationDate())
                .sign(Algorithm.HMAC256(secretKey));
        return token;
    }

    private String generateEstablishmentToken(Establishment establishment) {
        String token = JWT.create()
                .withSubject(establishment.getEmail())
                .withClaim("role", "ESTABLISHMENT")
                .withExpiresAt(expirationDate())
                .sign(Algorithm.HMAC256(secretKey));
        return token;
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(5).atZone(ZoneId.systemDefault()).toInstant();
    }

    private boolean validateToken(String token) {
        JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token)
                .getSubject();
        return true;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String sub = jwt.getSubject();
        String role = jwt.getClaim("role").asString();

        if (sub != null && role != null) {
            if (role.equals("USER")) {
                return new UsernamePasswordAuthenticationToken(sub, null, null);
            } else if (role.equals("ESTABLISHMENT")) {
                return new UsernamePasswordAuthenticationToken(sub, null, null);
            }
        }

        return null;
    }
}