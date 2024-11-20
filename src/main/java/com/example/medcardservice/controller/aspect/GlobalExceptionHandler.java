package com.example.medcardservice.controller.aspect;

import com.example.medcardservice.model.ErrorDetails;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(ConstraintViolationException ex) {
        String message = "Ошибка валидации: " + ex.getConstraintViolations().iterator().next().getMessage();
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
