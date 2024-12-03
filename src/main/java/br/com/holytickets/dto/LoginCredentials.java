package br.com.holytickets.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredentials {
    @NotBlank(message = "Email cannot be empty.")
    private String email;
    @NotBlank(message = "Password cannot be empty.")
    private String password;
}
