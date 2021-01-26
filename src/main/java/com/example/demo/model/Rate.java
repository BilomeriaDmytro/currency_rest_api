package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Rate")
public class Rate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @OneToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "currency_id")
    private Currency currency;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "date")
    private LocalDate date;
}
