package com.example.task.recommendations;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationsServiceImpl implements RecommendationsService {
    @Override
    public List<String> getCryptosSymbolsByNormalizedRange(Order order) throws IOException {
        List<Crypto> cryptos = getCryptosByNormalizedRange(order);
        return cryptos.stream().map(crypto -> crypto.getSymbol()).collect(Collectors.toList());
    }

    public List<Crypto> getCryptosByNormalizedRange(Order order) throws IOException {
        List<Crypto> cryptos = new ArrayList<>();
        List<String> symbols = CryptoReaderUtils.getAvailableCryptos();
        for (String symbol : symbols) {
            List<PriceEntry> entries = CryptoReaderUtils.getDataForCrypto(symbol);
            double normalizedRange = calculateNormalizedRange(entries);
            Crypto crypto = new Crypto();
            crypto.setSymbol(symbol);
            crypto.setNormalizedRange(normalizedRange);
            cryptos.add(crypto);
        }

        Collections.sort(cryptos, new CryptoNormalizedRangeComparator());
        return cryptos;
    }

    private static double calculateNormalizedRange(List<PriceEntry> entries) {
        double min = Double.MAX_VALUE;
        double max = 0;
        for (PriceEntry entry : entries) {
            if (entry.getPrice() < min) {
                min = entry.getPrice();
            }
            if (entry.getPrice() > max) {
                max = entry.getPrice();
            }
        }
        return (max - min) / min;
    }

    @Override
    public double getPrice(String symbol, CryptoFilterCondition cryptoFilter) throws CryptoNotFoundException {
        try {
            List<PriceEntry> entries = CryptoReaderUtils.getDataForCrypto(symbol);
            return switch(cryptoFilter) {
                case OLDEST -> entries.stream().min(new PriceEntryDateComparator()).get().getPrice();
                case NEWEST -> entries.stream().max(new PriceEntryDateComparator()).get().getPrice();
                case MIN -> entries.stream().min(new PriceEntryPriceComparator()).get().getPrice();
                case MAX -> entries.stream().max(new PriceEntryPriceComparator()).get().getPrice();
            };
        } catch (FileNotFoundException e) {
            throw new CryptoNotFoundException();
        }
    }

    @Override
    public String getCryptoWithHighestNormalizedRangeForDate(Date date) throws InvalidDateException, IOException {

        List<Crypto> cryptos = new ArrayList<>();
        List<String> symbols = CryptoReaderUtils.getAvailableCryptos();
        for (String symbol : symbols) {
            List<PriceEntry> entries = CryptoReaderUtils.getDataForCrypto(symbol);
            List<PriceEntry> filteredForDate = entries.stream().filter(entry -> sameDay(entry.getDate(), date)).collect(Collectors.toList());
            double normalizedRange = calculateNormalizedRange(filteredForDate);
            Crypto crypto = new Crypto();
            crypto.setSymbol(symbol);
            crypto.setNormalizedRange(normalizedRange);
            cryptos.add(crypto);
        }
        Collections.sort(cryptos, new CryptoNormalizedRangeComparator());
        return cryptos.get(0).getSymbol();
    }

    private boolean sameDay(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

}
