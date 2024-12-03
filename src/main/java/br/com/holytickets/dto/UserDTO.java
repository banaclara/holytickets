package br.com.holytickets.dto;

import br.com.holytickets.validation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    @NotBlank(message = "User name cannot be empty.")
    private String name;

    @UniqueEmail
    @NotBlank(message = "User email cannot be empty.")
    private String email;

    @NotBlank(message = "User password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    private List<TicketDTO> tickets;
}
