package br.com.holytickets.controllers;

import br.com.holytickets.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
private final RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<Character, String>> getSeatChart(@PathVariable UUID id) {
        Map<Character, String> namedRows = roomService.getDefaultSeatChart(id);
        return ResponseEntity.ok(namedRows);
    }
}
