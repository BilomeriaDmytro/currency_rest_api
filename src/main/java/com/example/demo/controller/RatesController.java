package com.example.demo.controller;

import com.example.demo.model.Rate;
import com.example.demo.service.RateService;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import com.example.demo.service.exceptionHandler.exception.DateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
public class RatesController {

    @Autowired
    private RateService rateService;

    //get list of currency rates
    @GetMapping("api/u/get/currencyRate/all")
    public List<Rate> getAllRates() {

        return rateService.findAllRates();
    }

    //get list of currency rates by currency code
    @GetMapping("api/u/get/currencyRate/byCode")
    public List<Rate> getCurrencyRatesByCode(@RequestParam("currencyCode") String currencyCode)
            throws IOException, CurrencyNotFoundException {

        return rateService.getCurrencyRates(currencyCode);
    }

    //get currency rate by currency code and date
    @GetMapping("api/u/get/currencyRate/onDate")
    public Rate getCurrencyRateOnDate(@RequestParam("currencyCode") String currencyCode,
                                      @RequestParam("date") String date)
            throws IOException, CurrencyNotFoundException, DateFormatException {

        return rateService.getCurrencyRateOnDate(currencyCode,date);
    }

    //get currency rates in period by currency code, date from, date to
    @GetMapping("api/u/get/currencyRate/inPeriod")
    public List<Rate> getCurrencyRatesInPeriod(@RequestParam("currencyCode") String currencyCode,
                                               @RequestParam("dateFrom") String dateFrom,
                                               @RequestParam("dateTo") String dateTo)
            throws IOException, CurrencyNotFoundException, DateFormatException {

        return rateService.getCurrencyRatesInPeriod(currencyCode, dateFrom, dateTo);
    }

    //----------------------------------------------------

    @GetMapping("api/a/getCurrencyData")
    public String getCurrencyRateData(@RequestParam("currencyCode") String currencyCode)
            throws IOException, CurrencyNotFoundException, DateFormatException {

        return rateService.getCurrencyRateData(currencyCode);
    }

}
