package br.com.holytickets.services;

import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    public Establishment register(Establishment dto) {
        return establishmentRepository.save(dto);
    }
}
