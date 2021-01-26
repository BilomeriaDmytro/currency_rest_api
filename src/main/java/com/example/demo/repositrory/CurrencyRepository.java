package com.example.demo.repositrory;

import com.example.demo.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    Currency findByName(String currencyName);

    Currency findByCode(String currency_code);
}
