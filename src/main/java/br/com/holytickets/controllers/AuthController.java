package br.com.holytickets.controllers;

import br.com.holytickets.dto.*;
import br.com.holytickets.services.AuthService;
import br.com.holytickets.services.EstablishmentService;
import br.com.holytickets.services.UserService;
import br.com.holytickets.utils.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final EstablishmentService establishmentService;
    private final AuthService authService;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTO createdUser = userService.create(userDTO);
        String token = jwtUtils.generateToken(createdUser.getEmail(), createdUser.getId(), "USER");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register/establishment")
    public ResponseEntity<String> registerEstablishment(@RequestBody @Valid EstablishmentRegisterDTO establishmentDTO) {
        establishmentDTO.setPassword(passwordEncoder.encode(establishmentDTO.getPassword()));
        EstablishmentDTO createdEstablishment = establishmentService.register(establishmentDTO);
        String token = jwtUtils.generateToken(createdEstablishment.getEmail(), createdEstablishment.getId(), "ESTABLISHMENT");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginCredentials credentials) {
        AuthDetails authDetails = authService.validateAuth(credentials.getEmail(), credentials.getPassword());
        String token = jwtUtils.generateToken(credentials.getEmail(), authDetails.getId(), authDetails.getRole());
        return ResponseEntity.ok(token);
    }
}
