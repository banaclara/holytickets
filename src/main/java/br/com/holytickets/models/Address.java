package br.com.holytickets.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Size(min = 5, message = "Street name must contain at least 5 characters")
    private String street;

    private String number;

    @Size(min = 4, message = "City name must contain at least 4 characters")
    private String city;

    @Size(min = 2, message = "State name must contain at least 2 characters")
    private String state;

    @Size(min = 2, message = "Country name must contain at least 2 characters")
    private String country;
    private String cep;
}