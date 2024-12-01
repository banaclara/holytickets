package br.com.holytickets.dto;

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
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

<<<<<<< HEAD
=======
    @Min(value = 1, message = "Capacity must be at least 1.")
    private Integer capacity;

>>>>>>> f952281bc16756d936aed031f4a7a38606c463b6
    @NotBlank(message = "Contact number cannot be empty.")
    private String contactNumber;

    private AddressDTO address;

    private RoomDTO room;
}