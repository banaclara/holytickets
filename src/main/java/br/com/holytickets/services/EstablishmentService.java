package br.com.holytickets.services;

import br.com.holytickets.client.ViaCepClient;
import br.com.holytickets.dto.AddressDTO;
import br.com.holytickets.dto.CepDTO;
import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.dto.EstablishmentRegisterDTO;
import br.com.holytickets.exception.ConflictException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import br.com.holytickets.utils.Converter;
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
    private final ViaCepClient viaCepClient;


    public EstablishmentDTO register(EstablishmentRegisterDTO dto) {
        if (!establishmentRepository.findByName(dto.getName()).isEmpty()) {
            throw new ConflictException("An establishment with the name " + dto.getName() + " already exists.");
        }

        int rows = dto.getRoom().getRows();
        int columns = dto.getRoom().getColumns();

        if (rows <= 0 || columns <= 0) {
            throw new ConflictException("Room rows and columns must be greater than zero.");
        } else if (rows > 26) {
            throw new ConflictException("Room rows can't be more than 26.");
        }

        String cep = dto.getCep();
        CepDTO cepDTO = viaCepClient.buscarEnderecoPorCep(cep);

        if (cepDTO == null || cepDTO.getLogradouro() == null) {
            throw new ResourceNotFoundException("Invalid CEP: No address found for CEP " + cep);
        }

        AddressDTO addressDTO = converter.mapCepToAddressDTO(cepDTO, dto.getNumber());

        Establishment establishment = converter.convertToEntity(dto, addressDTO);
        return converter.convertToDTO(establishmentRepository.save(establishment));
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
