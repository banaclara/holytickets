package br.com.holytickets.services;

import br.com.holytickets.dto.*;
import br.com.holytickets.exception.BadRequestException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Seat;
import br.com.holytickets.models.Ticket;
import br.com.holytickets.models.User;
import br.com.holytickets.repositories.SeatRepository;
import br.com.holytickets.repositories.TicketRepository;
import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.utils.Converter;
import br.com.holytickets.utils.DateFormatter;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final ScheduleService scheduleService;
    private final UserRepository userRepository;
    private final Converter converter;
    private final DateFormatter dateFormatter;

    public TicketDTO sellTicket(TicketDTO ticketDTO) {
        UUID scheduleId = ticketDTO.getSeat().getScheduleId();
        if (scheduleId == null) {
            throw new BadRequestException("The schedule ID was not provided.");
        }
        ScheduleDTO scheduleDTO = scheduleService.findById(scheduleId);

        Optional<List<String>> seatsSold = seatRepository.findSeatsSold(scheduleDTO.getId());
        String seatNumber = ticketDTO.getSeat().getSeatNumber();
        seatsSold.ifPresent(seats -> {
            if (seats.contains(seatNumber)) {
                throw new BadRequestException("Seat " + seatNumber + " unavailable");
            }
        });

        UUID userId = ticketDTO.getUserId();
        if (userId == null) {
            throw new BadRequestException("The user ID was not provided.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));


        ticketDTO.setPurchaseDate(LocalDateTime.now());

        Seat seat = converter.convertToEntity(ticketDTO.getSeat());
        seat = seatRepository.save(seat);

        ticketDTO.setSeat(converter.convertToDTO(seat));

        UserDTO userDTO = converter.convertToDTO(user);
        ticketDTO.setUserId(userDTO.getId());

        Ticket ticket = converter.convertToEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);

        ticketDTO = converter.convertToDTO(ticket);

        return ticketDTO;
    }

    public PrintTicketDTO printTicket(UUID id) {
        Tuple tuple = ticketRepository.findRawPrintTicketById(id);

        String exhibition = dateFormatter.convertTimestampToString(tuple.get("exhibition_date", Timestamp.class));
        String purchase = dateFormatter.convertTimestampToString(tuple.get("purchase_date", Timestamp.class));

        return new PrintTicketDTO(
                tuple.get("id", UUID.class),
                tuple.get("establishment_name", String.class),
                tuple.get("event_title", String.class),
                exhibition,
                tuple.get("seat_number", String.class),
                purchase,
                tuple.get("user_name", String.class)
        );
    }
}
