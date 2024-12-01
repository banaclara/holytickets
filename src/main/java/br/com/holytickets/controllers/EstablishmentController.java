package br.com.holytickets.controllers;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.services.EstablishmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    @GetMapping("/")
    public ResponseEntity<List<EstablishmentDTO>> listAll() {
        List<EstablishmentDTO> e = establishmentService.list();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentDTO> findEstablishmentById(@PathVariable UUID id) {
        EstablishmentDTO establishmentDTO = establishmentService.findByID(id);
        return ResponseEntity.ok(establishmentDTO);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Map<Character, String>> getSeatChart(@PathVariable UUID id) throws JsonProcessingException {
        Map<Character, String> namedRows = establishmentService.getDefaultSeatChart(id);
        return ResponseEntity.ok(namedRows);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<EstablishmentDTO>> findEstablishmentByName(@PathVariable String name) {
        List<EstablishmentDTO> establishments = establishmentService.findByName(name.trim().toLowerCase());
        return ResponseEntity.ok(establishments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentDTO> update(@PathVariable UUID id,
                                                   @RequestBody @Valid EstablishmentDTO establishmentDTO) {
        EstablishmentDTO updated = establishmentService.update(id, establishmentDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        establishmentService.deleteStab(id);
        return ResponseEntity.noContent().build();
    }


}
