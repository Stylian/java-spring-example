package com.example.task.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "BALANCE")
    private double balance;
    @Column(name = "CURRENCY", nullable = false)
    private String currency;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    public Account() {
    }

    public Account(long id, double balance, String currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Account(double balance, String currency) {
        this(-1L, balance, currency);
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
