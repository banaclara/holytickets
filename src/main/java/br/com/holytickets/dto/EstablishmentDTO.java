package br.com.holytickets.dto;

import br.com.holytickets.validation.UniqueEmail;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class EstablishmentDTO {
    private UUID id;

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @Email(message = "Email must be valid.")
    @NotBlank(message = "Email cannot be empty.")
    @UniqueEmail
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    @NotBlank(message = "Contact number cannot be empty.")
    private String contactNumber;

    private AddressDTO address;

    private RoomDTO room;

    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP must be valid (e.g., 12345-678).")
    private String cep;

}