package com.backend.banorte.exceptions;

import com.backend.banorte.domain.response.GenericResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler extends Exception {





    @ExceptionHandler(CustomMessageException.class)
    public ResponseEntity<GenericResponse> accountValidationException (CustomMessageException ex){
        GenericResponse data = new GenericResponse();
        data.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);

    }





}
