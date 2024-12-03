package br.com.holytickets.validation.implementation;

import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.repositories.EstablishmentRepository;
import br.com.holytickets.validation.EmailValidation;
import br.com.holytickets.validation.UniqueEmail;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final EmailValidation emailValidation;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true;
        }
        return emailValidation.isUnique(email);
    }
}
