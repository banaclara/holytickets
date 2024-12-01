package br.com.holytickets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class EstablishmentDTO {
    private UUID id;

    @NotEmpty(message = "Name cannot be empty.")
    private String name;

    @Email(message = "Email must be valid.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    @NotEmpty(message = "Contact number cannot be empty.")
    private String contactNumber;

    private AddressDTO address;

    @NotEmpty(message = "Room characteristics cannot be empty")
    private RoomDTO room;

}