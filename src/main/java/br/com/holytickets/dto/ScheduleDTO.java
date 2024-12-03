package br.com.holytickets.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private UUID id;

    @Schema(example = "dd/mm/aaaa HH:MM")
    private String exhibitionDate;


    private UUID eventId;
    @JsonIgnore
    private List<SeatDTO> seats;
}
