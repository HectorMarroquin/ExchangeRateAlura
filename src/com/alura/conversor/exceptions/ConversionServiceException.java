package com.alura.conversor.exceptions;

public class ConversionServiceException extends RuntimeException {

    public ConversionServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
