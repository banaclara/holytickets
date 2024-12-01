package br.com.holytickets.controllers;

import br.com.holytickets.dto.ScheduleDTO;
import br.com.holytickets.services.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/register")
    public ResponseEntity<ScheduleDTO> register (@Valid @RequestBody ScheduleDTO scheduleDTO){
        ScheduleDTO createdSchedule = scheduleService.register(scheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ScheduleDTO> findById(@PathVariable UUID id){
        ScheduleDTO scheduleDTO = scheduleService.findById(id);
        return ResponseEntity.ok(scheduleDTO);
    }
}
