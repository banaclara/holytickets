package br.com.holytickets.utils;

import br.com.holytickets.dto.AddressDTO;
import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.models.Address;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.models.Event;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDirector(eventDTO.getDirector());
        event.setCasting(eventDTO.getCasting());
        event.setDescription(eventDTO.getDescription());
        return event;
    }

    public EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDirector(event.getDirector());
        eventDTO.setCasting(event.getCasting());
        eventDTO.setDescription(event.getDescription());
        return eventDTO;
    }

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
