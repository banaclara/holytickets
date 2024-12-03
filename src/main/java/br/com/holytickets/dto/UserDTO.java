package br.com.holytickets.dto;

import br.com.holytickets.validation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    @UniqueEmail
    private String email;
    private String password;
    @JsonIgnore
    private List<TicketDTO> tickets;
}
