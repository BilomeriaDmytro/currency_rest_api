package com.example.demo.service.exceptionHandler.exception;

public class CurrencyNotFoundException extends Exception{
    public CurrencyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
