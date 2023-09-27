package com.example.task.services;

import org.springframework.stereotype.Service;

public interface CurrencyConversionService {

    double convert(double amount, String fromCurrency, String toCurrency);
}
