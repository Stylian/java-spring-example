package com.example.task.services;

import com.example.task.dtos.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecommendationsServiceTests {
    @Autowired
    RecommendationsService recommendationsService;
    @Test
    public void testGetCryptosByNormalizedRange() {
        List<String> ls =recommendationsService.getCryptosByNormalizedRange(Order.DESC);
        System.out.print(ls);
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
