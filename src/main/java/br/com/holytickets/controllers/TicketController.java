package br.com.holytickets.controllers;

import br.com.holytickets.dto.TicketDTO;
import br.com.holytickets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/sell")
    public ResponseEntity<TicketDTO> sellTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO ticketSold = ticketService.sellTicket(ticketDTO);
        return ResponseEntity.ok(ticketSold);

    }
}
