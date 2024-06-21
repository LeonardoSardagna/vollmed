package com.volmed.vollmed_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity seErro404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity seErro400(MethodArgumentNotValidException exception){
        var erro = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(MensagemErro::new).toList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +exception.getLocalizedMessage());
    }

    private record MensagemErro(String codigo, String mensagem){
        private MensagemErro (FieldError error){
            this(error.getCode(), error.getDefaultMessage());
        }
    }
}
