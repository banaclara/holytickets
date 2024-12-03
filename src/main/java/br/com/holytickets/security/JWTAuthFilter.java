package br.com.holytickets.security;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import br.com.holytickets.services.AuthService;
import br.com.holytickets.utils.JWTUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null && jwtUtils.validateToken(token)) {
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

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String email = jwt.getSubject();
        String id = jwt.getClaim("id").asString();
        String role = jwt.getClaim("role").asString();

        if (email != null && role != null) {
            return new UsernamePasswordAuthenticationToken(email, null, List.of(() -> role));
        }

        return null;
    }
}
