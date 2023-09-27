package com.example.task.services;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
    @Override
    public double convert(double amount, String fromCurrency, String toCurrency) {

       //TODO
        return amount;
    }
}
