package br.com.holytickets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private UUID id;

    @NotBlank(message = "O título do evento não pode ser vazio.")
    @Size(max = 100, message = "O título do evento não pode ter mais de 100 caracteres.")
    private String title;

    @NotBlank(message = "O nome do diretor não pode ser vazio.")
    private String director;

    @NotBlank(message = "O elenco não pode ser vazio.")
    private String casting;

    @NotBlank(message = "A descrição do evento não pode ser vazia.")
    private String description;

    private UUID establishmentId;
}
