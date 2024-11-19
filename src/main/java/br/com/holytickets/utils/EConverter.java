package br.com.holytickets.utils;

import br.com.holytickets.dto.AddressDTO;
import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.models.Address;
import br.com.holytickets.models.Establishment;
import org.springframework.stereotype.Component;

@Component
public class EConverter {
    public EstablishmentDTO convertToDTO(Establishment establishment) {
        return new EstablishmentDTO(
                establishment.getId(),
                establishment.getName(),
                establishment.getEmail(),
                establishment.getPassword(),
                establishment.getCapacity(),
                establishment.getContactNumber(),
                convertToDTO(establishment.getAddress())
        );
    }

    public AddressDTO convertToDTO(Address adress) {
        return new AddressDTO(
                adress.getStreet(),
                adress.getNumber(),
                adress.getCity(),
                adress.getState(),
                adress.getCountry()
        );
    }

    public Establishment convertToEntity(EstablishmentDTO establishmentDTO) {
        return new Establishment(
                establishmentDTO.getId(),
                establishmentDTO.getName(),
                establishmentDTO.getEmail(),
                establishmentDTO.getPassword(),
                establishmentDTO.getCapacity(),
                establishmentDTO.getContactNumber(),
                convertToEntity(establishmentDTO.getAddress())
        );
    }

    public Address convertToEntity(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getCity(),
                addressDTO.getState(),
                addressDTO.getCountry()
        );
    }
}
