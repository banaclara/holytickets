package br.com.holytickets.controllers;

import br.com.holytickets.dto.UpdateUserDTO;
import br.com.holytickets.dto.UserDTO;
import br.com.holytickets.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> listAll() {
        List<UserDTO> e = userService.list();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable UUID id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/editUser/{id}")

    public ResponseEntity<UserDTO> update(@PathVariable UUID id, @RequestBody @Valid UpdateUserDTO userDTO) {

        UserDTO updated = userService.update(id, userDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
