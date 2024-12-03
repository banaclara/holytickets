package br.com.holytickets.controllers;

import br.com.holytickets.dto.PrintTicketDTO;
import br.com.holytickets.dto.TicketDTO;
import br.com.holytickets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/print/{id}")
    public ResponseEntity<PrintTicketDTO> printTicket(@PathVariable("id") UUID id) {
        PrintTicketDTO printTicketDTO = ticketService.printTicket(id);
        return ResponseEntity.ok(printTicketDTO);
    }
}
