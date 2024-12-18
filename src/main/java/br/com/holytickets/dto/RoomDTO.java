package br.com.holytickets.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @NotBlank(message = "Room must have at least one column")
    private int columns;

    @NotBlank(message = "Room must have 1 to 26 rows")
    private int rows;
}
