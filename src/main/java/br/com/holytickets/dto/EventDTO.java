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
    @NotBlank(message = "Event title cannot be empty.")
    @Size(max = 100, message = "Event title cannot be longer than 100 characters.")
    private String title;
    @NotBlank(message = "Director name cannot be empty.")
    private String director;
    @NotBlank(message = "Casting cannot be empty.")
    private String casting;
    @NotBlank(message = "Event description cannot be empty.")
    private String description;
    private UUID establishmentId;
}