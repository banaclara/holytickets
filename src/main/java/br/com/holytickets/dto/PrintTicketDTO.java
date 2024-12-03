package br.com.holytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintTicketDTO {
    UUID id;
    String establishment_name;
    String event_title;
    String exhibition_date;
    String seat_number;
    String purchase_date;
    String user_name;
}
