package br.com.holytickets.services;

import br.com.holytickets.exception.InvalidCredentialsException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentService establishmentService;

    public String findRoleByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "USER";
        } else if (establishmentRepository.findByEmail(email).isPresent()) {
            return "ESTABLISHMENT";
        }

        throw new ResourceNotFoundException("Email " + email + " not found for any user or establishment.");
    }

    public void validateCredentials(String email, String password, String role) {
        boolean isValid = false;

        if ("USER".equals(role)) {
            isValid = userService.validateCredentials(email, password);
        } else if ("ESTABLISHMENT".equals(role)) {
            isValid = establishmentService.validateCredentials(email, password);
        }

        if (!isValid) {
            throw new InvalidCredentialsException("Invalid Credentials: Email or Password");
        }
    }
}
