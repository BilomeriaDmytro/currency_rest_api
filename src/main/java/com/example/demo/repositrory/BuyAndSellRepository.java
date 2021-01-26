package com.example.demo.repositrory;

import com.example.demo.model.BuyAndSell;
import com.example.demo.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BuyAndSellRepository extends JpaRepository<BuyAndSell,Long> {
    List<BuyAndSell> findByCurrency(Currency currency);

    BuyAndSell findByCurrencyAndDate(Currency currency, LocalDate date);

    @Query(value = "SELECT * FROM buy_sell WHERE currency_id = :id AND date BETWEEN :startTime AND :endTime", nativeQuery = true)
    List<BuyAndSell> findByCurrencyAndStartDateBetween(@Param("id") Long id,
                                                       @Param("startTime") LocalDate startTime,
                                                       @Param("endTime") LocalDate endTime);
}
