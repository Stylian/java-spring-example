package com.example.task.recommendations;

import com.example.task.recommendations.Crypto;
import com.example.task.recommendations.CryptoFilterCondition;
import com.example.task.recommendations.Order;
import com.example.task.recommendations.CryptoNotFoundException;
import com.example.task.recommendations.InvalidDateException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface RecommendationsService {
    List<String> getCryptosSymbolsByNormalizedRange(Order order) throws IOException;

    List<Crypto> getCryptosByNormalizedRange(Order order) throws IOException;

    double getPrice(String crypto, CryptoFilterCondition cryptoFilter) throws CryptoNotFoundException;

    String getCryptoWithHighestNormalizedRangeForDate(Date date) throws InvalidDateException, IOException;

}
