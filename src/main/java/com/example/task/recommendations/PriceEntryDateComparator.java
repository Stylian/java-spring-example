package com.example.task.recommendations;

import java.util.Comparator;

public class PriceEntryDateComparator implements Comparator<PriceEntry> {
    @Override
    public int compare(PriceEntry o1, PriceEntry o2) {
        return o1.getDate().before(o2.getDate()) ? -1 : 1;
    }
}
