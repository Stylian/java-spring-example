package com.example.task.services;

import com.example.task.dtos.CryptoFilterCondition;
import com.example.task.dtos.Order;
import com.example.task.exceptions.CryptoNotFoundException;
import com.example.task.exceptions.InvalidDateException;

import java.util.Date;
import java.util.List;

public interface RecommendationsService {
    List<String> getCryptosByNormalizedRange(Order order);
    double getPrice(String crypto, CryptoFilterCondition cryptoFilter) throws CryptoNotFoundException;
    String getCryptoWithHighestNormalizedRangeForDate(Date date) throws InvalidDateException;

}
