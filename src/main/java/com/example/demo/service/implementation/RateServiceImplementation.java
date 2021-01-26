package com.example.demo.service.implementation;

import com.example.demo.dto.RateDTO;
import com.example.demo.model.Currency;
import com.example.demo.model.Rate;
import com.example.demo.repositrory.RateRepository;
import com.example.demo.service.CurrencyService;
import com.example.demo.service.RateService;
import com.example.demo.service.apiCalls.CurrencyApiCalls;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import com.example.demo.service.exceptionHandler.exception.DateFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RateServiceImplementation implements RateService {

    RateRepository rateRepository;
    CurrencyApiCalls currencyApiCalls;
    CurrencyService currencyService;

    @Autowired
    public RateServiceImplementation(RateRepository rateRepository, CurrencyApiCalls currencyApiCalls, CurrencyService currencyService) {
        this.rateRepository = rateRepository;
        this.currencyApiCalls = currencyApiCalls;
        this.currencyService = currencyService;
    }

    @Override
    public List<Rate> findAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public List<Rate> getCurrencyRates(String currencyCode)
            throws CurrencyNotFoundException, IOException {

        Currency currency = currencyService.getCurrency(currencyCode);
        return rateRepository.findByCurrency(currency);
    }

    @Override
    public Rate getCurrencyRateOnDate(String currencyCode, String date)
            throws CurrencyNotFoundException, IOException, DateFormatException {

        Currency currency = currencyService.getCurrency(currencyCode);
        return rateRepository.findByCurrencyAndDate(currency, currencyApiCalls.parseStringToDate(date));
    }

    @Override
    public List<Rate> getCurrencyRatesInPeriod(String currencyCode, String dateFrom, String dateTo)
            throws CurrencyNotFoundException, IOException, DateFormatException {

        Currency currency = currencyService.getCurrency(currencyCode);
        LocalDate startDate = currencyApiCalls.parseStringToDate(dateFrom);
        LocalDate finishDate = currencyApiCalls.parseStringToDate(dateTo);
        if(startDate.isAfter(finishDate)){
            throw new DateFormatException(startDate, finishDate);
        }
        return rateRepository.findByCurrencyAndStartDateBetween(currency.getId(), startDate, finishDate);
    }

    //-------------------------------------------------------------------------------

    @Override
    public String getCurrencyRateData(String currencyCode)
            throws IOException, CurrencyNotFoundException, DateFormatException {

        Currency currency = currencyService.getCurrency(currencyCode);
        List<RateDTO> rateDTOList = currencyApiCalls.getCurrencyRateDTOList(currency);
        if(rateDTOList == null){
            return "Something went wrong!";
        }
        List<Rate> rates = new ArrayList<Rate>();
        for (RateDTO rateDTO : rateDTOList) {
            LocalDate date = currencyApiCalls.parseStringToDate(rateDTO.getDate().replace("-",""));
                Rate rate = new Rate();
                rate.setCurrency(currency);
                rate.setDate(date);
                rate.setRate(rateDTO.getRate());
                rates.add(rate);
        }
        log.info("Adding {} rates to database: {}",currency, rates.size());
        rateRepository.saveAll(rates);
        log.info("Rates added to database.");
        return "Done!";
    }
}
