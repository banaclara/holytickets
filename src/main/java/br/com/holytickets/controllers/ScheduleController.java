package br.com.holytickets.controllers;

import br.com.holytickets.dto.ScheduleDTO;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.services.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // Endpoint para registrar um agendamento
    @PostMapping("/register")
    public ResponseEntity<ScheduleDTO> register(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO createdSchedule = scheduleService.register(scheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    // Endpoint para buscar um agendamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> findById(@PathVariable UUID id) {
        try {
            ScheduleDTO scheduleDTO = scheduleService.findById(id);
            return ResponseEntity.ok(scheduleDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Endpoint para listar todos os agendamentos
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        List<ScheduleDTO> schedules = scheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        try {
            scheduleService.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build(); // Retorna 404 Not Found se o agendamento não for encontrado
        }
    }

    // Endpoint para atualizar um agendamento
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> update(@PathVariable UUID id, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        try {
            ScheduleDTO updatedSchedule = scheduleService.update(id, scheduleDTO);
            return ResponseEntity.ok(updatedSchedule); // Retorna o agendamento atualizado
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null); // Retorna 404 Not Found se o agendamento não for encontrado
        }
    }
}