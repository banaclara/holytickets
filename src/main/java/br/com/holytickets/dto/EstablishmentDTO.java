package br.com.holytickets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class EstablishmentDTO {
    private UUID id;

    @NotNull(message = "Name is required")
    private String name;

    @Email(message = "Invalid format")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    private String contactNumber;

    private AddressDTO address;
}
