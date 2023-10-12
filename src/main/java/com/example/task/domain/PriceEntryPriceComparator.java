package com.example.task.domain;

import com.example.task.domain.PriceEntry;

import java.util.Comparator;

/**
 * ascending comparator
 */
public class PriceEntryPriceComparator implements Comparator<PriceEntry> {
    @Override
    public int compare(PriceEntry o1, PriceEntry o2) {
        return o1.getPrice() < o2.getPrice() ? -1 : 1;
    }
}
