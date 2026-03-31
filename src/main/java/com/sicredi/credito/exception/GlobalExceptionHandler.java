package com.sicredi.credito.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoServiceException.class)
    public ResponseEntity<?> handleProdutoServiceException(ProdutoServiceException ex) {
        return ResponseEntity
                .status(503)
                .body(new ErrorResponse(ex.getMessage(), LocalDateTime.now().toString()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage(), LocalDateTime.now().toString()));
    }
}