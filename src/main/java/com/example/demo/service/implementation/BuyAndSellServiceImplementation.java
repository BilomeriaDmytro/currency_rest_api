package com.example.demo.service.implementation;

import com.example.demo.dto.BuyAndSellDTO;
import com.example.demo.model.BuyAndSell;
import com.example.demo.model.Currency;
import com.example.demo.repositrory.BuyAndSellRepository;
import com.example.demo.service.BuyAndSellService;
import com.example.demo.service.CurrencyService;
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

@Slf4j
@Service
public class BuyAndSellServiceImplementation implements BuyAndSellService {

    private BuyAndSellRepository buyAndSellRepository;
    private CurrencyService currencyService;
    private CurrencyApiCalls currencyApiCalls;

    @Autowired
    public BuyAndSellServiceImplementation(BuyAndSellRepository buyAndSellRepository, CurrencyService currencyService, CurrencyApiCalls currencyApiCalls) {
        this.buyAndSellRepository = buyAndSellRepository;
        this.currencyService = currencyService;
        this.currencyApiCalls = currencyApiCalls;
    }

    @Override
    public List<BuyAndSell> getAllBuyAndSell() {
        return buyAndSellRepository.findAll();
    }

    @Override
    public List<BuyAndSell> getBuyAndSellByCode(String currencyCode)
            throws CurrencyNotFoundException, IOException {

        Currency currency = currencyService.getCurrency(currencyCode);
        return buyAndSellRepository.findByCurrency(currency);
    }

    @Override
    public BuyAndSell getBuyAndSellOnDateByCode(String currencyCode, String date)
            throws CurrencyNotFoundException, IOException, DateFormatException {

        Currency currency = currencyService.getCurrency(currencyCode);
        LocalDate onDate = currencyApiCalls.parseStringToDate(date);
        return buyAndSellRepository.findByCurrencyAndDate(currency,onDate);
    }

    @Override
    public List<BuyAndSell> getBuyAndSellListInPeriodByCode(String currencyCode, String dateFrom, String dateTo)
            throws CurrencyNotFoundException, IOException, DateFormatException {

        Currency currency = currencyService.getCurrency(currencyCode);
        LocalDate startDate = currencyApiCalls.parseStringToDate(dateFrom);
        LocalDate finishDate = currencyApiCalls.parseStringToDate(dateTo);
        if(startDate.isAfter(finishDate)){
            throw new DateFormatException(startDate,finishDate);
        }
        return buyAndSellRepository.findByCurrencyAndStartDateBetween(currency.getId(),startDate,finishDate);
    }

    //-------------------------------------------------------------------------------------------

    @Override
    public String uploadBuyAndSellData(String currencyCode)
            throws IOException, CurrencyNotFoundException, DateFormatException {

        Currency currency = currencyService.getCurrency(currencyCode);
        List<BuyAndSellDTO> buyAndSellDTOList = currencyApiCalls.getBuyAndSellDTOList(currency);
        if(buyAndSellDTOList == null){
            return "Something went wrong!";
        }
        List<BuyAndSell> buyAndSells = new ArrayList<BuyAndSell>();
        for (BuyAndSellDTO buyAndSellDTO : buyAndSellDTOList) {
            LocalDate date = currencyApiCalls.parseStringToDate(buyAndSellDTO.getDate().replace("-",""));
                BuyAndSell buyAndSell = new BuyAndSell();
                buyAndSell.setCurrency(currency);
                buyAndSell.setDate(date);
                buyAndSell.setBuy(buyAndSellDTO.getBuy());
                buyAndSell.setSell(buyAndSellDTO.getSell());
                buyAndSells.add(buyAndSell);
        }
        log.info("Adding {} buy and sell row to database: {}",currency, buyAndSells.size());
        buyAndSellRepository.saveAll(buyAndSells);
        log.info("Buy and sell rows added to database.");
        return "Done!";
    }

}
