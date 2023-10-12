package com.example.task.domain;

import com.example.task.domain.Crypto;

import java.util.Comparator;

/**
 * descending comparator
 */
public class CryptoNormalizedRangeComparator implements Comparator<Crypto> {
    @Override
    public int compare(Crypto o1, Crypto o2) {
        return (o1.getNormalizedRange() < o2.getNormalizedRange()) ? 1 : -1;
    }
}
