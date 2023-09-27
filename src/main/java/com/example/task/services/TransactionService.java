package com.example.task.services;

import com.example.task.dtos.TransactionRequest;
import com.example.task.entities.Transaction;
import com.example.task.exceptions.AccountNotExistsException;
import com.example.task.exceptions.IdenticalAccountsException;
import com.example.task.exceptions.InsufficientBalanceException;

public interface TransactionService {
    Transaction executeTransfer(TransactionRequest transactionRequest) throws AccountNotExistsException,
            IdenticalAccountsException, InsufficientBalanceException;
}
