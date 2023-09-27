package com.example.task.controllers;

import com.example.task.dtos.TransactionRequest;
import com.example.task.entities.Transaction;
import com.example.task.exceptions.AccountNotExistsException;
import com.example.task.exceptions.IdenticalAccountsException;
import com.example.task.exceptions.InsufficientBalanceException;
import com.example.task.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    @ResponseBody
    public ResponseEntity<String> executeTransaction(@RequestParam TransactionRequest transactionRequest) {

        try {
            Transaction transaction = transactionService.executeTransfer(transactionRequest);
            return new ResponseEntity("The transaction has been completed successfully with" +
                    " transaction_id: " + transaction.getId(),
                    new HttpHeaders(),
                    HttpStatus.CREATED);
        } catch (AccountNotExistsException e) {
            return new ResponseEntity("One of the specified accounts was not found",
                    new HttpHeaders(),
                    HttpStatus.NOT_FOUND);
        } catch (IdenticalAccountsException e) {
            return new ResponseEntity("The source and target accounts specified are identical",
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        } catch (InsufficientBalanceException e) {
            return new ResponseEntity("The source account's balance is not sufficient for the transaction",
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }

    }
}
