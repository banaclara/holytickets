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
    UUID ticketId;
    String establishmentName;
    String eventName;
    LocalDateTime exhibitionDate;
    String seatNumber;
    LocalDateTime purchaseDate;
    String userName;
}
