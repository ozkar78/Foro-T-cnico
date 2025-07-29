package com.aluracursos.Proyecto.Foro.config;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationDTO>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<ErrorValidationDTO> errors = e.getFieldErrors().stream()
                .map(ErrorValidationDTO::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessageDTO> handleBusinessValidation(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageDTO> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
    }

    public record ErrorValidationDTO(String field, String error) {
        public ErrorValidationDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    public record ErrorMessageDTO(String message) {}
}