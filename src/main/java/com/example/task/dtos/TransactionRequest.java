package com.example.task.dtos;

import com.example.task.entities.Account;

public class TransactionRequest {
    private long sourceAccountId;
    private long targetAccountId;
    private double amount;
    private String currency;

    public TransactionRequest(long sourceAccountId, long targetAccountId, double amount, String currency) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.currency = currency;
    }
    public TransactionRequest(Account sourceAccount, Account targetAccount, double amount, String currency) {
        this.sourceAccountId = sourceAccount.getId();
        this.targetAccountId = targetAccount.getId();
        this.amount = amount;
        this.currency = currency;
    }

    public long getSourceAccountId() {
        return sourceAccountId;
    }
    public long getTargetAccountId() {
        return targetAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
