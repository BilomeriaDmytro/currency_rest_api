package com.example.demo.service;

import com.example.demo.model.BuyAndSell;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import com.example.demo.service.exceptionHandler.exception.DateFormatException;
import java.io.IOException;
import java.util.List;

public interface BuyAndSellService {

    List<BuyAndSell> getAllBuyAndSell();

    List<BuyAndSell> getBuyAndSellByCode(String currencyCode) throws CurrencyNotFoundException, IOException;

    BuyAndSell getBuyAndSellOnDateByCode(String currencyCode, String date) throws CurrencyNotFoundException, IOException, DateFormatException;

    List<BuyAndSell> getBuyAndSellListInPeriodByCode(String currencyCode, String dateFrom, String dateTo) throws CurrencyNotFoundException, IOException, DateFormatException;

    //-----------------------------

    String uploadBuyAndSellData(String currencyCode) throws IOException, CurrencyNotFoundException, DateFormatException;
}
