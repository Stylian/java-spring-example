package com.example.task.recommendations;

import com.opencsv.bean.CsvBind;

import java.util.Date;

public class PriceEntry {
    @CsvBind
    private long timestamp;
    @CsvBind
    private String symbol;
    @CsvBind
    private double price;

    public Date getDate() {
        return new Date(timestamp);
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}
