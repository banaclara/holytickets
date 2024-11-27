package br.com.holytickets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class EstablishmentDTO {
    private UUID id;

    @NotEmpty(message = "O nome não pode ser vazio.")
    private String name;

    @Email(message = "O e-mail deve ser válido.")
    @NotEmpty(message = "O e-mail não pode ser vazio.")
    private String email;

    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String password;


    private Integer capacity;

    @NotEmpty(message = "O número de contato não pode ser vazio.")
    private String contactNumber;

    private AddressDTO address;
}
