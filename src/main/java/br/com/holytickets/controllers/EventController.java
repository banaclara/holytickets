package br.com.holytickets.controllers;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.models.Event;
import br.com.holytickets.services.EventService;
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
    public ResponseEntity<Event> register(@RequestBody EventDTO eventDTO) {
        Event event = eventService.register(eventDTO);
        return ResponseEntity.ok(event);
    }
    @GetMapping("/list")
    public ResponseEntity<List<EventDTO>> listAll() {
        List<EventDTO> events = eventService.listAll();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<EventDTO> findByID(@PathVariable UUID id) {
        return eventService.findByID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<List<EventDTO>> findByTitle(@PathVariable String title) {
        List<EventDTO> events = eventService.findByTitle(title);
        if (events.isEmpty()) {
            return ResponseEntity.noContent().build(); // Caso nenhum evento seja encontrado
        }
        return ResponseEntity.ok(events);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable UUID id, @RequestBody EventDTO eventDTO) {
        Optional<EventDTO> updatedEvent = eventService.update(id, eventDTO);

        return updatedEvent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        boolean isDeleted = eventService.deleteEvent(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
