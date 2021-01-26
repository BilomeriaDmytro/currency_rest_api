package com.example.demo.service;

import com.example.demo.dto.CurrencyDTO;
import com.example.demo.model.Currency;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrency() throws IOException;

    Currency getCurrency(String currency_code) throws IOException, CurrencyNotFoundException;

    void save(CurrencyDTO currencyDTO);

}
