package com.example.medcardservice.controller.aspect;


import com.example.medcardservice.exception.DiseaseNotFoundException;
import com.example.medcardservice.exception.DiseaseUpdateException;
import com.example.medcardservice.exception.Mkb10EntryNotFoundException;
import com.example.medcardservice.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DiseaseExceptionHandler {

    @ExceptionHandler(DiseaseNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionNotFoundDiseaseHandler() {
        String message = "Ошибка при чтении данных: заболевания у пациента не найдены.";
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Mkb10EntryNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionNotFoundMkb10EntryHandler(Mkb10EntryNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                "Недопустимый ввод: " + ex.getMessage()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DiseaseUpdateException.class)
    public ResponseEntity<ErrorDetails> exceptionNotUpdateDiseaseHandler() {
        String message = "Ошибка при обновлении данных: не удалось обновить заболевания для пациента.";
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



}
