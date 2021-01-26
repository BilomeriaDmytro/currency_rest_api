package com.example.demo.service;

import com.example.demo.model.Rate;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import com.example.demo.service.exceptionHandler.exception.DateFormatException;
import java.io.IOException;
import java.util.List;

public interface RateService {

    List<Rate> findAllRates();

    List<Rate> getCurrencyRates(String currencyCode) throws CurrencyNotFoundException, IOException;

    Rate getCurrencyRateOnDate(String currencyCode, String date) throws CurrencyNotFoundException, IOException, DateFormatException;

    List<Rate> getCurrencyRatesInPeriod(String currencyCode, String dateFrom, String dateTo) throws CurrencyNotFoundException, IOException, DateFormatException;

    String getCurrencyRateData(String currencyCode) throws IOException, CurrencyNotFoundException, DateFormatException;
}
