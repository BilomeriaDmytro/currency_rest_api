package com.example.demo.repositrory;

import com.example.demo.model.Currency;
import com.example.demo.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {
    List<Rate> findByCurrency(Currency currency);

    Rate findByCurrencyAndDate(Currency currency, LocalDate date);

    @Query(value = "SELECT * FROM rate WHERE currency_id = :id AND date BETWEEN :startTime AND :endTime", nativeQuery = true)
    List<Rate> findByCurrencyAndStartDateBetween(@Param("id") Long id,
                                                 @Param("startTime") LocalDate startTime,
                                                 @Param("endTime") LocalDate endTime);
}
