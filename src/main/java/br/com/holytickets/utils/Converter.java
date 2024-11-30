package br.com.holytickets.utils;

import br.com.holytickets.dto.*;
import br.com.holytickets.models.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class Converter {

    public EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDirector(event.getDirector());
        eventDTO.setCasting(event.getCasting());
        eventDTO.setDescription(event.getDescription());

        if (event.getEstablishment() != null) {
            eventDTO.setEstablishmentId(event.getEstablishment().getId());
        }

        return eventDTO;
    }

    public Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDirector(eventDTO.getDirector());
        event.setCasting(eventDTO.getCasting());
        event.setDescription(eventDTO.getDescription());

        if (eventDTO.getEstablishmentId() != null) {
            Establishment establishment = new Establishment();
            establishment.setId(eventDTO.getEstablishmentId());
            event.setEstablishment(establishment);
        }

        return event;
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

    public UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getTickets() != null ?
                        user.getTickets().stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public User convertToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getTickets() != null ?
                        userDTO.getTickets().stream()
                                .map(this::convertToEntity)
                                .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public TicketDTO convertToDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getPurchaseDate(),
                convertToDTO(ticket.getUser()),
                convertToDTO(ticket.getSeat())
        );
    }

    public Ticket convertToEntity(TicketDTO ticketDTO) {
        return new Ticket(
                ticketDTO.getId(),
                ticketDTO.getPurchaseDate(),
                convertToEntity(ticketDTO.getUser()),
                convertToEntity(ticketDTO.getSeat())
        );
    }

    public SeatDTO convertToDTO(Seat seat) {
        return new SeatDTO(
                seat.getId(),
                seat.getSeatNumber(),
                seat.isAvailable(),
                convertToDTO(seat.getSchedule())
        );
    }

    public Seat convertToEntity(SeatDTO seatDTO) {
        return new Seat(
                seatDTO.getId(),
                seatDTO.getSeatNumber(),
                seatDTO.isAvailable(),
                convertToEntity(seatDTO.getSchedule())
        );
    }

    public ScheduleDTO convertToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getExhibitionDate(),
                schedule.getRoom(),
                convertToDTO(schedule.getEvent()),
                schedule.getSeats() != null ?
                        schedule.getSeats().stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        return new Schedule(
                scheduleDTO.getId(),
                scheduleDTO.getExhibitionDate(),
                scheduleDTO.getRoom(),
                convertToEntity(scheduleDTO.getEvent()),
                scheduleDTO.getSeats() != null ?
                        scheduleDTO.getSeats().stream()
                                .map(this::convertToEntity)
                                .collect(Collectors.toList()) : Collections.emptyList()
        );
    }
}
