package com.example.demo.service.exceptionHandler.exception;

import java.time.LocalDate;

public class DateFormatException extends Exception {
    public DateFormatException(String date) {
        super("Check date format for input - "+ date +". It should be string -  yyyyddmm. Example: 20100104, for 04.01.2010.");
    }

    public DateFormatException(LocalDate date1, LocalDate date2) {
        super("Start date(" + date1 + ") is after finish date("+ date2 + ").");
    }
}
