package com.example.task.recommendations;

import java.util.Comparator;

public class PriceEntryPriceComparator implements Comparator<PriceEntry> {
    @Override
    public int compare(PriceEntry o1, PriceEntry o2) {
        return o1.getPrice() < o2.getPrice() ? -1 : 1;
    }
}
