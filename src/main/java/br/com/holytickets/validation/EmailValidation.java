package br.com.holytickets.validation;

import br.com.holytickets.repositories.EstablishmentRepository;
import br.com.holytickets.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailValidation {
    private final UserRepository userRepository;
    private final EstablishmentRepository establishmentRepository;

    @Transactional
    public boolean isUnique(String email) {
        return !userRepository.existsByEmail(email) && !establishmentRepository.existsByEmail(email);
    }
}
