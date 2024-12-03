package br.com.holytickets.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @NotNull(message = "Row is requited")
    @Min(value = 1, message = "O valor minimo é um")
    @Max(value = 26, message = "o maximo é 26")
    private Integer rows;

    @NotNull(message = "Column is requited")
    @Min(value = 1, message = "O valor minimo é um")
    private Integer columns;
}