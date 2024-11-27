package br.com.holytickets.controllers;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.services.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    @PostMapping("/register")
    public ResponseEntity<EstablishmentDTO> signup(@RequestBody EstablishmentDTO dto) {
        EstablishmentDTO e = establishmentService.register(dto);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/")
    public ResponseEntity<List<EstablishmentDTO>> listAll() {
        List<EstablishmentDTO> e = establishmentService.list();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EstablishmentDTO>> findEstablishment(@PathVariable UUID id) {
        Optional<EstablishmentDTO> e = establishmentService.findByID(id);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<EstablishmentDTO>> findEstablishmentByName(@PathVariable String name) {
        List<EstablishmentDTO> e = establishmentService.findByName(name);
        return ResponseEntity.ok(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentDTO> update(@PathVariable UUID id, @RequestBody EstablishmentDTO establishmentDTO) {
        Optional<EstablishmentDTO> updated = establishmentService.update(id, establishmentDTO);

        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
