package br.com.holytickets.controllers;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.models.Event;
import br.com.holytickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/register")
    public ResponseEntity<EventDTO> register(@Valid @RequestBody EventDTO eventDTO) {
        EventDTO createdEvent = eventService.register(eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EventDTO>> listAll() {
        List<EventDTO> events = eventService.listAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<EventDTO> findByID(@PathVariable UUID id) {
        EventDTO eventDTO = eventService.findByID(id);
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<List<EventDTO>> findByTitle(@PathVariable String title) {
        List<EventDTO> events = eventService.findByTitle(title);
        return ResponseEntity.ok(events);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable UUID id, @RequestBody EventDTO eventDTO) {
        EventDTO updatedEvent = eventService.update(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
