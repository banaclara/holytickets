package br.com.holytickets.security;

import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//@RequiredArgsConstructor
//public class EstablishmentAuthDetailsService implements UserDetailsService {
//    private final EstablishmentRepository establishmentRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Establishment> establishment = establishmentRepository.findByEmail(email);
//        if (establishment.isPresent()) {
//            return new EstablishmentAuthDetails(establishment);
//        }
//    }
//}