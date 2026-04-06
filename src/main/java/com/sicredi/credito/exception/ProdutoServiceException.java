package com.sicredi.credito.exception;

public class ProdutoServiceException extends RuntimeException {

    public ProdutoServiceException(String message) {
        super(message);
    }

    public ProdutoServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}