package br.com.holytickets.controllers;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.dto.LoginCredentials;
import br.com.holytickets.dto.UserDTO;
import br.com.holytickets.services.AuthService;
import br.com.holytickets.services.EstablishmentService;
import br.com.holytickets.services.UserService;
import br.com.holytickets.utils.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTO createdUser = userService.create(userDTO);
        String token = jwtUtils.generateToken(createdUser.getEmail(), "USER");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register/establishment")
    public ResponseEntity<String> registerEstablishment(@RequestBody @Valid EstablishmentDTO establishmentDTO) {
        establishmentDTO.setPassword(passwordEncoder.encode(establishmentDTO.getPassword()));
        EstablishmentDTO createdEstablishment = establishmentService.register(establishmentDTO);
        String token = jwtUtils.generateToken(createdEstablishment.getEmail(), "ESTABLISHMENT");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials credentials) {
        Optional<String> role = authService.findRoleByEmail(credentials.getEmail());
        if (role.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        boolean isValid = role.get().equals("USER")
                ? userService.validateCredentials(credentials.getEmail(), credentials.getPassword())
                : establishmentService.validateCredentials(credentials.getEmail(), credentials.getPassword());

        if (!isValid) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtils.generateToken(credentials.getEmail(), role.get());
        return ResponseEntity.ok(token);
    }
}

