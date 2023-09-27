package com.example.task.services;

import com.example.task.dtos.TransactionRequest;
import com.example.task.entities.Account;
import com.example.task.entities.Transaction;
import com.example.task.exceptions.AccountNotExistsException;
import com.example.task.exceptions.IdenticalAccountsException;
import com.example.task.exceptions.InsufficientBalanceException;
import com.example.task.repositories.AccountRepository;
import com.example.task.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Override
    public Transaction executeTransfer(TransactionRequest transactionRequest) throws AccountNotExistsException,
            IdenticalAccountsException, InsufficientBalanceException {

        Account sourceAccount = accountRepository.findById(transactionRequest.getSourceAccountId());
        Account targetAccount = accountRepository.findById(transactionRequest.getTargetAccountId());
        if (sourceAccount == null || targetAccount == null) {
            throw new AccountNotExistsException();
        }

        if (sourceAccount.equals(targetAccount)) {
            throw new IdenticalAccountsException();
        }

        if (sourceAccount.getBalance() < requestAmountToSourceCurrency(transactionRequest, sourceAccount.getCurrency())) {
            throw new InsufficientBalanceException();
        }

        return makeTransfer(sourceAccount, targetAccount, transactionRequest.getAmount(), transactionRequest.getCurrency());
    }

    private double requestAmountToSourceCurrency(TransactionRequest transactionRequest, String sourceCurrency) {
        return currencyConversionService.convert(
                transactionRequest.getAmount(),
                transactionRequest.getCurrency(),
                sourceCurrency);
    }

    private Transaction makeTransfer(Account sourceAccount, Account targetAccount, double amount, String currency) {
        double debitedAmountToSource = currencyConversionService.convert(amount, currency, sourceAccount.getCurrency());
        sourceAccount.setBalance(sourceAccount.getBalance() - debitedAmountToSource);

        double creditedAmountToTarget = currencyConversionService.convert(amount, currency, targetAccount.getCurrency());
        targetAccount.setBalance(targetAccount.getBalance() + creditedAmountToTarget);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transaction transaction = new Transaction(sourceAccount, targetAccount, amount, currency);
        return transactionRepository.save(transaction);
    }
}
