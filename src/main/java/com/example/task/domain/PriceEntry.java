package com.example.task.domain;

import com.opencsv.bean.CsvBind;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceEntry that = (PriceEntry) o;

        if (timestamp != that.timestamp) return false;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
    }
}
