package br.com.holytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthDetails {
    private String email;
    private UUID id;
    private String role;
}
