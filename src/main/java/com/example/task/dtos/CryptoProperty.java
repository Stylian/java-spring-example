package com.example.task.dtos;

public enum CryptoProperty {
    NORMALIZED_RANGE;

    public static CryptoProperty getEnum(String value) {
        for (CryptoProperty v : values())
            if (v.name().equalsIgnoreCase(value)) return v;

        return null;
    }
}
