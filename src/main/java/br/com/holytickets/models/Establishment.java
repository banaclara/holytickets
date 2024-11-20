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
@Table(name = "establishments")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid format")
    private String email;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    private String contactNumber;

    @Embedded
    private Address address;
}
