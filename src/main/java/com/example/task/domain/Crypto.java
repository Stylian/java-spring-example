package com.example.task.domain;

public class Crypto {
    private String symbol;
    private double normalizedRange;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getNormalizedRange() {
        return normalizedRange;
    }

    public void setNormalizedRange(double normalizedRange) {
        this.normalizedRange = normalizedRange;
    }
}
