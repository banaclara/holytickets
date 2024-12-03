package br.com.holytickets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Define o status HTTP 409
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
