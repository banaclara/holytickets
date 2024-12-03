package br.com.holytickets.dto;

import br.com.holytickets.validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredentials {

    @NotBlank(message = "User email cannot be empty.")
    private String email;

    @NotBlank(message = "User password cannot be empty.")
    private String password;
}
