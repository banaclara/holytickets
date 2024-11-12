package br.com.holytickets.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Email(message = "Invalid format")
    private String email;

    @Embedded
    private Address address;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    private String contactNumber;
}
