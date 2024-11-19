package br.com.holytickets.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDTO {
    @Size(min = 5, message = "Street name must contain at least 5 characters")
    private String street;

    private String number;

    @Size(min = 4, message = "City name must contain at least 4 characters")
    private String city;

    @Size(min = 2, message = "State name must contain at least 2 characters")
    private String state;

    @Size(min = 2, message = "Country name must contain at least 2 characters")
    private String country;
}
