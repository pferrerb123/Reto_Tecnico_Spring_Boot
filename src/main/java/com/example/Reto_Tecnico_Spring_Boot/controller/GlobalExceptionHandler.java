package com.example.Reto_Tecnico_Spring_Boot.controller;

import com.example.Reto_Tecnico_Spring_Boot.model.ErrorResponse;
import com.example.Reto_Tecnico_Spring_Boot.model.TipoCambioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TipoCambioException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleTipoCambioException(TipoCambioException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("Ocurrió un error interno: " + e.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }
}