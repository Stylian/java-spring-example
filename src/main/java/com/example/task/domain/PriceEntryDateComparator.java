package com.example.task.domain;

import com.example.task.domain.PriceEntry;

import java.util.Comparator;
/**
 * ascending comparator
 */
public class PriceEntryDateComparator implements Comparator<PriceEntry> {
    @Override
    public int compare(PriceEntry o1, PriceEntry o2) {
        return o1.getDate().before(o2.getDate()) ? -1 : 1;
    }
}
