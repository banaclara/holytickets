package br.com.holytickets.dto;

import lombok.Data;

@Data
public class CepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}