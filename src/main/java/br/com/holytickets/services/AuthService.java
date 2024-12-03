package br.com.holytickets.services;

import br.com.holytickets.dto.AuthDetails;
import br.com.holytickets.exception.InvalidCredentialsException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final EstablishmentRepository establishmentRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthDetails validateAuth(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new AuthDetails(email, user.getId(), "USER"))
                .or(() -> establishmentRepository.findByEmail(email)
                        .filter(establishment -> passwordEncoder.matches(password, establishment.getPassword()))
                        .map(establishment -> new AuthDetails(email, establishment.getId(), "ESTABLISHMENT")))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
    }
}
