package com.example.demo.service.implementation;

import com.example.demo.dto.CurrencyDTO;
import com.example.demo.model.Currency;
import com.example.demo.repositrory.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import java.util.List;

@EnableScheduling
@Slf4j
@Service
public class CurrencyServiceImplementation implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrency(String currencyCode)
            throws  CurrencyNotFoundException {

        Currency currency = currencyRepository.findByCode(currencyCode);
        if(currency == null){
            log.warn("No such currency found - {}", currencyCode);
            throw new CurrencyNotFoundException("Currency with code - " + currencyCode + " not found");
        }
        return currency;
    }

    @Override
    public void save(CurrencyDTO currencyDTO) {
        Currency currency = new Currency();
        currency.setName(currencyDTO.getName());
        currency.setCode(currencyDTO.getCode());
        currencyRepository.save(currency);
    }

}
