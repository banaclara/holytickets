package br.com.holytickets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CustomError>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<CustomError> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new CustomError(error.getField(), error.getDefaultMessage()));
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<List<CustomError>> handleResourceNotFound(ResourceNotFoundException ex) {
        List<CustomError> errors = new ArrayList<>();
        errors.add(new CustomError("Resource", ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<List<CustomError>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        List<CustomError> errors = new ArrayList<>();
        errors.add(new CustomError("Error", ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<CustomError>> handleGlobalException(Exception ex) {
        List<CustomError> errors = new ArrayList<>();
        errors.add(new CustomError("Error", "Ocorreu um erro interno no servidor."));
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<List<CustomError>> handleBadRequestException(BadRequestException ex) {
        List<CustomError> errors = new ArrayList<>();
        errors.add(new CustomError("Request", ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<List<CustomError>> handleConflictException(ConflictException ex) {
        List<CustomError> errors = new ArrayList<>();
        errors.add(new CustomError("Conflict", ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
