package com.example.task.dtos;

public enum Order {
    ASC,
    DESC
    ;
    public static Order getEnum(String value) {
        for (Order v : values())
            if (v.name().equalsIgnoreCase(value)) return v;

        return null;
    }
}
