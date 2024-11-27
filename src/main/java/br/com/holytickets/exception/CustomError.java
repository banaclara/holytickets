package br.com.holytickets.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomError {
    private String field;
    private String message;


}
