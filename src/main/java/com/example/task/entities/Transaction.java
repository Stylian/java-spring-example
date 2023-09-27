package com.example.task.entities;


import jakarta.persistence.*;
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;
    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private Account targetAccount;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    public Transaction(Account sourceAccount, Account targetAccount, double amount, String currency) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
