package com.example.task.recommendations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationsServiceImpl implements RecommendationsService {
    @Autowired
    private CryptoCsvReader cryptoCsvReader;
    @Autowired
    private CryptosRepository cryptosRepository;

    @EventListener(classes = ApplicationStartedEvent.class)
    public void loadDataToCache() {
        try {
            List<String> symbols = cryptoCsvReader.getAvailableCryptos();
            for (String symbol : symbols) {
                for (CryptoFilterCondition cryptoFilterCondition : CryptoFilterCondition.values()) {
                    cryptosRepository.getPriceForSymbolAndFilter(symbol, cryptoFilterCondition);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CryptoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getCryptosSymbolsByNormalizedRange(Order order) throws IOException {
        List<Crypto> cryptos = getCryptosByNormalizedRange(order);
        return cryptos.stream().map(crypto -> crypto.getSymbol()).collect(Collectors.toList());
    }

    public List<Crypto> getCryptosByNormalizedRange(Order order) throws IOException {
        return cryptosRepository.getCryptosByNormalizedRange(order);
    }

    @Override
    public double getPrice(String symbol, CryptoFilterCondition cryptoFilter) throws CryptoNotFoundException {
        return cryptosRepository.getPriceForSymbolAndFilter(symbol, cryptoFilter);
    }

    @Override
    public String getCryptoWithHighestNormalizedRangeForDate(Date date) throws InvalidDateException, IOException {
        return cryptosRepository.getCryptoWithHighestNormalizedRangeForDate(date);
    }
}
