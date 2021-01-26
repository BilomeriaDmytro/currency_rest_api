package com.example.demo;

import com.example.demo.model.Currency;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {
	@Autowired
	private static CurrencyService currencyService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
