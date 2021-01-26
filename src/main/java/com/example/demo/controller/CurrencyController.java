package com.example.demo.controller;

import com.example.demo.dto.CurrencyDTO;
import com.example.demo.model.Currency;
import com.example.demo.service.CurrencyService;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class CurrencyController {
    //date format yyyymmdd

    @Autowired
    private CurrencyService currencyService;

    //get list of all available currencies
    @GetMapping("api/u/get/currency/all")
    public List<Currency> getAllCurrency() throws IOException {

        return currencyService.getAllCurrency();
    }

    //get currency by code
    @GetMapping("api/u/get/currency/byCode")
    public Currency getCurrencyByCode(@RequestParam("currencyCode") String currencyCode)
            throws CurrencyNotFoundException, IOException {

            return currencyService.getCurrency(currencyCode);
    }

    ///-----------------------------------------------------------------------------------------------------

    @PostMapping("api/a/save/currency")
    public void saveCurrency(@RequestBody CurrencyDTO currencyDTO) {

        currencyService.save(currencyDTO);
    }

}
