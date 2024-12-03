package br.com.holytickets.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @Min(value = 1, message = "Min value for room columns is 1")
    private int columns;

    @Min(value = 1, message = "Min value for room rows is 1")
    @Max(value = 26, message = "Max value for room rows is 26")
    private int rows;
}
