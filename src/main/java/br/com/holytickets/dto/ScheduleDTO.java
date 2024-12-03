package br.com.holytickets.dto;

import br.com.holytickets.models.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private UUID id;
    private LocalDateTime exhibitionDate;
    private UUID eventId;
    @JsonIgnore
    private List<SeatDTO> seats;
}
