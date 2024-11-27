package br.com.holytickets.dto;

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
    private String room;
    private EventDTO event;
    private List<SeatDTO> seats;
}
