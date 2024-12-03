package br.com.holytickets.services;

import br.com.holytickets.dto.*;
import br.com.holytickets.exception.BadRequestException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Schedule;
import br.com.holytickets.models.Seat;
import br.com.holytickets.models.Ticket;
import br.com.holytickets.models.User;
import br.com.holytickets.repositories.SeatRepository;
import br.com.holytickets.repositories.TicketRepository;
import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;;
import java.time.format.DateTimeFormatter;
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

    public TicketDTO sellTicket(TicketDTO ticketDTO) {
        //verificar se o schedule existe
        UUID scheduleId = ticketDTO.getSeat().getScheduleId();
        if (scheduleId == null) {
            throw new BadRequestException("The schedule ID was not provided.");
        }
        ScheduleDTO scheduleDTO = scheduleService.findById(scheduleId);


        //verificar se o assento naquele schedule já foi vendido
        Optional<List<String>> seatsSold = seatRepository.findSeatsSold(scheduleDTO.getId());
        String seatNumber = ticketDTO.getSeat().getSeatNumber();
        seatsSold.ifPresent(seats -> {
            if (seats.contains(seatNumber)) {
                throw new IllegalArgumentException("Seat " + seatNumber + " unavailable");
            }
        });


        //verificar se o user existe
        UUID userId = ticketDTO.getUserId();
        if (userId == null) {
            throw new BadRequestException("The user ID was not provided.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));


        //se tudo passar, criar um ticket e instanciar o seat_sold
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
        Optional<PrintTicketDTO> ticketInfos = ticketRepository.getPrintTicketInfos(id);
        if (ticketInfos.isEmpty()){
            throw new ResourceNotFoundException("ticket with ID " + id + " not found.");
        }

        PrintTicketDTO printTicketDTO = ticketInfos.get();

        //formata data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedPurchaseDate = ticketInfos.get().getPurchaseDate().format(formatter);
        String formattedExhibitionDate = ticketInfos.get().getExhibitionDate().format(formatter);

        /*printedTicket.setEstablishmentName();
        printedTicket.setEventName();
        printedTicket.setExhibitionDate();
        printedTicket.setSeatNumber();
        printedTicket.setUserName();
        printedTicket.setPurchaseDate();*/

        return printTicketDTO;
    }
}
