package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "buy_sell")
public class BuyAndSell {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @OneToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "currency_id")
    private Currency currency;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "buy")
    private Double buy;

    @Column(name = "sell")
    private Double sell;
}
