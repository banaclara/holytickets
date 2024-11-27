package br.com.holytickets.services;

import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final EstablishmentRepository establishmentRepository;

    public Optional<String> findRoleByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return Optional.of("USER");
        } else if (establishmentRepository.findByEmail(email).isPresent()) {
            return Optional.of("ESTABLISHMENT");
        }
        return Optional.empty();
    }
}
