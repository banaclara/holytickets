package br.com.holytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private UUID id;
    private String seatNumber;
    private UUID scheduleId;
}
