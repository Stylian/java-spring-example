package com.example.task.recommendations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecommendationsServiceTests {
    @Autowired
    RecommendationsService recommendationsService;
    @Test
    public void testGetCryptosByNormalizedRange() throws IOException {
        List<Crypto> cryptos = recommendationsService.getCryptosByNormalizedRange(Order.DESC);
        Assertions.assertTrue(cryptos.get(0).getNormalizedRange() > cryptos.get(1).getNormalizedRange());
        Assertions.assertTrue(cryptos.get(1).getNormalizedRange() > cryptos.get(2).getNormalizedRange());
        Assertions.assertTrue(cryptos.get(2).getNormalizedRange() > cryptos.get(3).getNormalizedRange());
    }
    @Test
    public void testGetCryptoWithHighestNormalizedDateForSpecificDate() throws IOException {
        String symbol = recommendationsService.getCryptoWithHighestNormalizedRangeForDate(new Date(1642071600000L));
        Assertions.assertEquals("XRP", symbol);
    }
    @Test
    public void testGetOldestPrice() throws CryptoNotFoundException {
        double price = recommendationsService.getPrice("BTC", CryptoFilterCondition.OLDEST);
        Assertions.assertEquals(46813.21, price);
    }
    @Test
    public void testGetNewestPrice() throws CryptoNotFoundException {
        double price = recommendationsService.getPrice("BTC", CryptoFilterCondition.NEWEST);
        Assertions.assertEquals(38415.79, price);
    }
    @Test
    public void testGetHighestPrice() throws CryptoNotFoundException {
        double price = recommendationsService.getPrice("BTC", CryptoFilterCondition.MAX);
        Assertions.assertEquals(47722.66, price);
    }
    @Test
    public void testGetLowestPrice() throws CryptoNotFoundException {
        double price = recommendationsService.getPrice("BTC", CryptoFilterCondition.MIN);
        Assertions.assertEquals(33276.59, price);
    }
}
