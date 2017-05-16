package com.epambootcamp.woddoc.web;

public enum WeightUnit {
    KG("kg"),
    POUND("lb");

    private final String name;

    WeightUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
