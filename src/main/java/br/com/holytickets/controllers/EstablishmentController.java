package br.com.holytickets.controllers;

import br.com.holytickets.models.Establishment;
import br.com.holytickets.services.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establismentService;

    @PostMapping("/register")
    public ResponseEntity<Establishment> signup(@RequestBody Establishment dto) {
        Establishment e = establismentService.register(dto);
        return ResponseEntity.ok(e);
    }
}
