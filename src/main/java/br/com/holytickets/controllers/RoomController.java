package br.com.holytickets.controllers;

import br.com.holytickets.models.Establishment;
import br.com.holytickets.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{establishmentId}")
    public ResponseEntity<Map<Character, String>> getDefaultSeatChart(@PathVariable UUID establishmentId) {
        List<String> seatsSold = new ArrayList<>();
        Map<Character, String> seatChart = roomService.getDefaultSeatChart(establishmentId, seatsSold);
        return ResponseEntity.ok(seatChart);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<Map<Character, String>> getAvailableSeatsChart(@PathVariable UUID scheduleId) {
        Map<Character, String> seatChart = roomService.getAvailableSeatsChart(scheduleId);
        return ResponseEntity.ok(seatChart);
    }

    @GetMapping("/establishmentBySchedule/{id}")
    public ResponseEntity<Establishment> getEstabishmentBySchedule(@PathVariable UUID id) {
        Establishment establishment = roomService.getEstablishmentBySchedule(id);
        return ResponseEntity.ok(establishment);
    }
}
