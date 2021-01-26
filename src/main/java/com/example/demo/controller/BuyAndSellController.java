package com.example.demo.controller;

import com.example.demo.model.BuyAndSell;
import com.example.demo.service.BuyAndSellService;
import com.example.demo.service.exceptionHandler.exception.CurrencyNotFoundException;
import com.example.demo.service.exceptionHandler.exception.DateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BuyAndSellController {

    @Autowired
    BuyAndSellService buyAndSellService;

    //get currency buy and sell list
    @GetMapping("api/u/get/buyAndSell/all")
    public List<BuyAndSell> getAllBuyAndSell() {
        return buyAndSellService.getAllBuyAndSell();
    }

    //get currency buy and sell by currency code
    @GetMapping("api/u/get/buyAndSell/byCode")
    public List<BuyAndSell> getBuyAndSellByCode(@RequestParam("currencyCode") String currencyCode)
            throws CurrencyNotFoundException, IOException {

        return buyAndSellService.getBuyAndSellByCode(currencyCode);
    }

    //get currency buy and sell by currency code and date
    @GetMapping("api/u/get/buyAndSell/onDate")
    public BuyAndSell getBuyAndSellDate(@RequestParam("currencyCode") String currencyCode,
                                      @RequestParam("date") String date)
            throws CurrencyNotFoundException, IOException, DateFormatException {

        return buyAndSellService.getBuyAndSellOnDateByCode(currencyCode,date);
    }

    //get currency rates in period by currency code, date from, date to
    @GetMapping("api/u/get/buyAndSell/inPeriod")
    public List<BuyAndSell> getBuyAndSellInPeriod(@RequestParam("currencyCode") String currencyCode,
                                               @RequestParam("dateFrom") String dateFrom,
                                               @RequestParam("dateTo") String dateTo)
            throws CurrencyNotFoundException, IOException, DateFormatException {

        return buyAndSellService.getBuyAndSellListInPeriodByCode(currencyCode, dateFrom, dateTo);
    }

    //-------------------------------------------------

    //get currency buy and sell by currency code
    @GetMapping("api/a/get/buyAndSell/upload")
    public String uploadBuyAndSell(@RequestParam("currencyCode") String currencyCode)
            throws IOException, CurrencyNotFoundException, DateFormatException {

        return buyAndSellService.uploadBuyAndSellData(currencyCode);
    }

}
