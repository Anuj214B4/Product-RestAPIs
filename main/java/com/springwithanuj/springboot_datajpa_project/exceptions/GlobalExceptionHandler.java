package com.springwithanuj.springboot_datajpa_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> response = new LinkedHashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach(
                        err -> {
                            String fieldName = ((FieldError) err).getField();
                            String msg = err.getDefaultMessage();

                            response.put(fieldName, msg);
                        }
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
