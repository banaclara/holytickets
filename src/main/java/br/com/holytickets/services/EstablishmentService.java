package br.com.holytickets.services;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import br.com.holytickets.utils.EConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentService {
    private final EstablishmentRepository establishmentRepository;
    private final EConverter converter;

    public EstablishmentDTO register(EstablishmentDTO dto) {
        Establishment establishment = converter.convertToEntity(dto);
        return converter.convertToDTO(establishmentRepository.save(establishment));
    }
}
