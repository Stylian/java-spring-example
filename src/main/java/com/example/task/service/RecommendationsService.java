package com.example.task.service;

import com.example.task.domain.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface RecommendationsService {
    List<String> getCryptosSymbolsByNormalizedRange(Order order) throws IOException;

    List<Crypto> getCryptosByNormalizedRange(Order order) throws IOException;

    double getPrice(String crypto, CryptoFilterCondition cryptoFilter) throws CryptoNotFoundException;

    String getCryptoWithHighestNormalizedRangeForDate(Date date) throws InvalidDateException, IOException;

}
