package br.com.holytickets.services;

import br.com.holytickets.client.ViaCepClient;
import br.com.holytickets.dto.AddressDTO;
import br.com.holytickets.dto.CepDTO;
import br.com.holytickets.dto.EstablishmentDTO;
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
    private final PasswordEncoder passwordEncoder;
    private final ViaCepClient viaCepClient; // Dependência para o cliente do ViaCEP

    public EstablishmentDTO register(EstablishmentDTO dto) {
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

        // Busca os dados do endereço pelo CEP
        String cep = dto.getCep();
        CepDTO cepDTO = viaCepClient.buscarEnderecoPorCep(cep);

        // Verifica se a API retornou uma resposta válida
        if (cepDTO == null || cepDTO.getLogradouro() == null) {
            throw new ResourceNotFoundException("Invalid CEP: No address found for CEP " + cep);
        }

        // Mapeia o endereço e atualiza o DTO
        AddressDTO addressDTO = mapCepToAddressDTO(cepDTO);
        dto.setAddress(addressDTO);

        // Salva o estabelecimento
        Establishment establishment = converter.convertToEntity(dto);
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

        // Atualiza os dados do estabelecimento
        establishment.setName(establishmentDTO.getName());
        establishment.setEmail(establishmentDTO.getEmail());
        establishment.setContactNumber(establishmentDTO.getContactNumber());

        // Busca o endereço baseado no cep, se o cep foi modificado
        String cep = establishmentDTO.getCep();
        CepDTO cepDTO = viaCepClient.buscarEnderecoPorCep(cep);

        if (cepDTO == null || cepDTO.getLogradouro() == null) {
            throw new ResourceNotFoundException("Invalid CEP: No address found for CEP " + cep);
        }

        // Mapeia e atualiza o endereço no DTO
        AddressDTO addressDTO = mapCepToAddressDTO(cepDTO);
        establishmentDTO.setAddress(addressDTO);

        // Salva o estabelecimento atualizado
        Establishment updatedEstablishment = establishmentRepository.save(establishment);
        return converter.convertToDTO(updatedEstablishment);
    }

    public void deleteStab(UUID id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment with ID " + id + " not found."));

        establishmentRepository.delete(establishment);
    }

    // Método utilitário para converter CepDTO em AddressDTO
    private AddressDTO mapCepToAddressDTO(CepDTO cepDTO) {
        return new AddressDTO(
                cepDTO.getLogradouro(),  // street
                "S/N",                   // Número fixo; ajuste conforme necessário
                cepDTO.getLocalidade(),  // city
                cepDTO.getUf(),          // state
                "Brazil",
                cepDTO.getCep()
        );
    }

}
