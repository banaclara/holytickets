package br.com.holytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class EstablishmentDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Integer capacity;
    private String contactNumber;
    private AddressDTO address;
}
