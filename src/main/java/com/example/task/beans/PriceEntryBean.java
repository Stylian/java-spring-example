package com.example.task.beans;

import com.opencsv.bean.CsvBind;

import java.sql.Timestamp;

public class PriceEntryBean {
    @CsvBind
    private Timestamp timestamp;
    @CsvBind
    private String symbol;
    @CsvBind
    private double price;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}
