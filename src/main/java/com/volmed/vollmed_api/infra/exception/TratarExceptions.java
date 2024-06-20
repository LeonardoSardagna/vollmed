package com.volmed.vollmed_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity seErro404(){
        return ResponseEntity.notFound().build();
    }
}
