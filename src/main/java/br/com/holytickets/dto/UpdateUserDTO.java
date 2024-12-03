package br.com.holytickets.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {
    @NotBlank(message = "User name cannot be empty.")
    private String name;

    @NotBlank(message = "User email cannot be empty.")
    private String email;
}
