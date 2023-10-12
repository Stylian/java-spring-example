package com.example.task.domain;

public enum CryptoFilterCondition {
    OLDEST,
    NEWEST,
    MIN,
    MAX
    ;
    public static CryptoFilterCondition getEnum(String value) {
        for (CryptoFilterCondition v : values())
            if (v.name().equalsIgnoreCase(value)) return v;

        return null;
    }
}
