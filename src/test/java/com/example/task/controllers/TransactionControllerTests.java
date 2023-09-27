package com.example.task.controllers;

import com.example.task.controllers.TransactionController;
import com.example.task.dtos.TransactionRequest;
import com.example.task.entities.Account;
import com.example.task.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * needs docker running
 */

//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
class TransactionControllerTests {
//    @Container
//    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.30");
//
//    @DynamicPropertySource
//    static void dbProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
//        registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
//    }
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private TransactionController transactionController;
//
//    @Test
//    void testThatItWorks() {
//        Account sourceAccount = accountRepository.save(new Account(500, "EUR"));
//        Account targetAccount = accountRepository.save(new Account(2000, "EUR"));
//
//        TransactionRequest transactionRequest = new TransactionRequest(sourceAccount, targetAccount, 300, "EUR");
//        ResponseEntity<String> entity = transactionController.executeTransaction(transactionRequest);
//        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.CREATED);
//    }
//
//    @Test
//    void testInsufficientBalanceException() {
//        Account sourceAccount = accountRepository.save(new Account(500, "EUR"));
//        Account targetAccount = accountRepository.save(new Account(2000, "EUR"));
//
//        TransactionRequest transactionRequest = new TransactionRequest(sourceAccount, targetAccount, 700, "EUR");
//        ResponseEntity<String> entity = transactionController.executeTransaction(transactionRequest);
//        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.BAD_REQUEST);
//    }
//
//    @Test
//    void testAccountNotExistsException() {
//        Account sourceAccount = accountRepository.save(new Account(500, "EUR"));
//
//        TransactionRequest transactionRequest = new TransactionRequest(sourceAccount.getId(), 999999L, 300, "EUR");
//        ResponseEntity<String> entity = transactionController.executeTransaction(transactionRequest);
//        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void testIdenticalAccountsException() {
//        Account sourceAccount = accountRepository.save(new Account(500, "EUR"));
//
//        TransactionRequest transactionRequest = new TransactionRequest(sourceAccount, sourceAccount, 300, "EUR");
//        ResponseEntity<String> entity = transactionController.executeTransaction(transactionRequest);
//        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.BAD_REQUEST);
//    }

}
