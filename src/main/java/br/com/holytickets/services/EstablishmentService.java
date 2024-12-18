package br.com.holytickets.services;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.exception.ConflictException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import br.com.holytickets.utils.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstablishmentService {
    private final EstablishmentRepository establishmentRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;

    public EstablishmentDTO register(EstablishmentDTO dto) {

        if (!establishmentRepository.findByName(dto.getName()).isEmpty()) {
            throw new ConflictException("An establishment with the name " + dto.getName() + " already exists.");
        }
        Establishment establishment = converter.convertToEntity(dto);
        return converter.convertToDTO(establishmentRepository.save(establishment));
    }

    public boolean validateCredentials(String email, String password) {
        return establishmentRepository.findByEmail(email)
                .map(establishment -> passwordEncoder.matches(password, establishment.getPassword()))
                .orElse(false);
    }

    public List<EstablishmentDTO> list() {
        List<Establishment> establishments = establishmentRepository.findAll();
        if (establishments.isEmpty()) {
            throw new ResourceNotFoundException("No establishments found.");
        }
        return establishments.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public EstablishmentDTO findByID(UUID id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment with ID " + id + " not found."));

        return converter.convertToDTO(establishment);
    }

    public List<EstablishmentDTO> findByName(String name) {
        List<Establishment> establishments = establishmentRepository.findByName(name);

        if (establishments.isEmpty()) {
            throw new ResourceNotFoundException("No establishments found with the name: " + name);
        }

        return establishments.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public EstablishmentDTO update(UUID id, EstablishmentDTO establishmentDTO) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment with ID " + id + " not found."));

        establishment.setName(establishmentDTO.getName());
        establishment.setEmail(establishmentDTO.getEmail());
        establishment.setContactNumber(establishmentDTO.getContactNumber());

        Establishment updatedEstablishment = establishmentRepository.save(establishment);

        return converter.convertToDTO(updatedEstablishment);
    }

    public void deleteStab(UUID id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment with ID " + id + " not found."));

        establishmentRepository.delete(establishment);
    }

}
