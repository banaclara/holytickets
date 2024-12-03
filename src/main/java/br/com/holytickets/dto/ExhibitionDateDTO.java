package br.com.holytickets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExhibitionDateDTO {
    @Schema(example = "dd/mm/aaaa HH:MM")
    private String exhibitionDate;
}
