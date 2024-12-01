package br.com.holytickets.models;

import jakarta.persistence.*;
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
    private Integer rows;

    @NotNull(message = "Column is requited")
    private Integer columns;
}