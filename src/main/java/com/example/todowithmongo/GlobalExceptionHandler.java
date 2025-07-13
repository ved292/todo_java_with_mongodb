package com.example.todowithmongo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<String> handleUnrecognizedProperty(UnrecognizedPropertyException ex) {
        return new ResponseEntity<>("Invalid JSON field: " + ex.getPropertyName(), HttpStatus.BAD_REQUEST);
    }

}
