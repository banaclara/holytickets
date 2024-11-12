package br.com.holytickets.security;

import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentAuthDetailsService implements UserDetailsService {
    private final EstablishmentRepository establishmentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Establishment establishment = establishmentRepository.findByEmail(email);
        return new EstablishmentAuthDetails(establishment);
    }
}