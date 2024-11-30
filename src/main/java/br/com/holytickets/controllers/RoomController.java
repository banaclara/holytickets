package br.com.holytickets.controllers;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.models.Room;
import br.com.holytickets.services.EventService;
import br.com.holytickets.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<List<Character>> listRows(@PathVariable Integer capacity) {
        List<Character> rows = roomService.getRows(capacity);
        return ResponseEntity.ok(rows);
    }
}
