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

    public ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId((schedule.getId()));
        scheduleDTO.setExhibitionDate(schedule.getExhibitionDate());

        if (schedule.getEvent() != null) {
            scheduleDTO.setEventId(schedule.getEvent().getId());
        }

        if (schedule.getSeats() != null) {
            scheduleDTO.setSeats(
                    schedule.getSeats().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList()));
        } else {
            scheduleDTO.setSeats(
                    Collections.emptyList());
        }
        return scheduleDTO;
    }

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setExhibitionDate(scheduleDTO.getExhibitionDate());
        if (scheduleDTO.getEventId() != null) {
            Event event = new Event();
            event.setId(scheduleDTO.getEventId());
            schedule.setEvent(event);
        }
        if (scheduleDTO.getSeats() != null) {
            schedule.setSeats(scheduleDTO.getSeats().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList()));
        } else {
            schedule.setSeats(Collections.emptyList());
        }

        return schedule;
    }

    public EstablishmentDTO convertToDTO(Establishment establishment) {
        return new EstablishmentDTO(
                establishment.getId(),
                establishment.getName(),
                establishment.getEmail(),
                establishment.getPassword(),
                establishment.getContactNumber(),
                convertToDTO(establishment.getAddress()),
                convertToDTO(establishment.getRoom())
        );
    }

    public AddressDTO convertToDTO(Address address) {
        return new AddressDTO(
                address.getStreet(),
                address.getNumber(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }

    public Establishment convertToEntity(EstablishmentDTO establishmentDTO) {
        return new Establishment(
                establishmentDTO.getId(),
                establishmentDTO.getName(),
                establishmentDTO.getEmail(),
                establishmentDTO.getPassword(),
                establishmentDTO.getContactNumber(),
                convertToEntity(establishmentDTO.getAddress()),
                convertToEntity(establishmentDTO.getRoom())
        );
    }

    public Establishment convertToEntity(EstablishmentRegisterDTO establishmentDTO, AddressDTO addressDTO) {
        return new Establishment(
                establishmentDTO.getId(),
                establishmentDTO.getName(),
                establishmentDTO.getEmail(),
                establishmentDTO.getPassword(),
                establishmentDTO.getContactNumber(),
                convertToEntity(addressDTO),
                convertToEntity(establishmentDTO.getRoom())
        );
    }

    public AddressDTO mapCepToAddressDTO(CepDTO cepDTO) {
        return new AddressDTO(
                cepDTO.getLogradouro(),  // street
                "S/N",                   // Número fixo; ajuste conforme necessário
                cepDTO.getLocalidade(),  // city
                cepDTO.getUf(),          // state
                "Brazil"
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
                ticket.getUser().getId(),
                convertToDTO(ticket.getSeat())
        );
    }

    public Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setPurchaseDate(ticketDTO.getPurchaseDate());
        if (ticketDTO.getUserId() != null) {
            User user = new User();
            user.setId(ticketDTO.getUserId());
            ticket.setUser(user);
        }
        ticket.setSeat(convertToEntity(ticketDTO.getSeat()));
        return ticket;
    }

    public SeatDTO convertToDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(seat.getId());
        seatDTO.setSeatNumber(seat.getSeatNumber());
        seatDTO.setScheduleId(seat.getSchedule().getId());
        return seatDTO;
    }

    public Seat convertToEntity(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setId(seatDTO.getId());
        seat.setSeatNumber(seatDTO.getSeatNumber());
        if (seatDTO.getScheduleId() != null) {
            Schedule schedule = new Schedule();
            schedule.setId(seatDTO.getScheduleId());
            seat.setSchedule(schedule);
        }
        return seat;
    }

    public Room convertToEntity(RoomDTO roomDTO) {
        return new Room(
                roomDTO.getRows(),
                roomDTO.getColumns()

        );
    }

    public RoomDTO convertToDTO(Room room) {
        return new RoomDTO(
                room.getColumns(),
                room.getRows()
        );
    }
}
