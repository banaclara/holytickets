package br.com.holytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDTO {
    private String street;
    private String number;
    private String city;
    private String state;
    private String country;
    private String cep;
}
