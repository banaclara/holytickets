package br.com.holytickets.services;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstablishmentService {
    private final EstablishmentRepository establishmentRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;

    public EstablishmentDTO register(EstablishmentDTO dto) {
        Establishment establishment = converter.convertToEntity(dto);
        return converter.convertToDTO(establishmentRepository.save(establishment));
    }

    public boolean validateCredentials(String email, String password) {
        return establishmentRepository.findByEmail(email)
                .map(establishment -> passwordEncoder.matches(password, establishment.getPassword()))
                .orElse(false);
    }

    public List<EstablishmentDTO> list() {
        return establishmentRepository.findAll().stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EstablishmentDTO> findByID(UUID id) {
        return establishmentRepository.findById(id)
                .map(converter::convertToDTO);
    }

    public List<EstablishmentDTO> findByName(String name) {
        return establishmentRepository.findByName(name)
                .stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EstablishmentDTO> update(UUID id, EstablishmentDTO establishmentDTO) {
        return establishmentRepository.findById(id).map(establishment -> {
            establishment.setName(establishmentDTO.getName());
            establishment.setEmail(establishmentDTO.getEmail());
            establishment.setCapacity(establishmentDTO.getCapacity());
            establishment.setContactNumber(establishmentDTO.getContactNumber());


            Establishment updatedEstablishment = establishmentRepository.save(establishment);

            return converter.convertToDTO(updatedEstablishment);
        });
    }
}
