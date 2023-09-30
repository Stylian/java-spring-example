package com.example.task.recommendations;

import com.example.task.recommendations.*;
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
//
//    @Test
//    public void testExecuteTransferWithDifferentCurrencies() throws IdenticalAccountsException, InsufficientBalanceException, AccountNotExistsException {
//        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, OTHER_CURRENCY);
//        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);
//
//        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
//        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);
//
//        Recommendation transactionRequest = new Recommendation(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
//        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
//        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
//
//        Transaction result = transactionService.executeTransfer(transactionRequest);
//        Assertions.assertEquals(187.5, result.getSourceAccount().getBalance());
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    public void testIDenticalAccountsError() {
//        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);
//        Account targetAccount = new Account(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);
//
//        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
//        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);
//
//        Recommendation transactionRequest = new Recommendation(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
//        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
//        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
//
//        Assertions.assertThrows(IdenticalAccountsException.class, () -> {
//            transactionService.executeTransfer(transactionRequest);
//        });
//    }
//
//    @Test
//    public void testAccountDoesNotExistError() {
//        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);
//
//        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
//        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(null);
//
//        Recommendation transactionRequest = new Recommendation(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
//        Transaction transaction = new Transaction(sourceAccount, null, 250, CURRENCY);
//        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
//
//        Assertions.assertThrows(AccountNotExistsException.class, () -> {
//            transactionService.executeTransfer(transactionRequest);
//        });
//    }
//
//    @Test
//    public void testInsufficientBalanceError() {
//        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);
//        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);
//
//        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
//        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);
//
//        Recommendation transactionRequest = new Recommendation(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 700, CURRENCY);
//        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
//        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
//
//        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
//            transactionService.executeTransfer(transactionRequest);
//        });
//    }
//
//    @Test
//    public void testInsufficientBalanceWithDifferntCurrenciesError() {
//        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, OTHER_CURRENCY);
//        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);
//
//        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
//        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);
//
//        Recommendation transactionRequest = new Recommendation(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 450, CURRENCY);
//        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
//        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
//
//        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
//            transactionService.executeTransfer(transactionRequest);
//        });
//    }
}
