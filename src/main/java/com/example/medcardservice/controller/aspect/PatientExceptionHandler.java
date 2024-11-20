package com.example.medcardservice.controller.aspect;


import com.example.medcardservice.exception.PatientDuplicateException;
import com.example.medcardservice.exception.PatientGenderNotExistException;
import com.example.medcardservice.exception.PatientNotFoundException;
import com.example.medcardservice.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionNotFoundPatientHandler() {
        String message = "Пациент не найден.";
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientDuplicateException.class)
    public ResponseEntity<ErrorDetails> exceptionDuplicatePatientHandler() {
        String message = "Пациент с данным номером полиса уже существует.";
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientGenderNotExistException.class)
    public ResponseEntity<ErrorDetails> exceptionGenderPatientHandler() {
        String message = "Неверное значение пола.";
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
