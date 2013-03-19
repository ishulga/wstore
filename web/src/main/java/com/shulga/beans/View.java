package com.shulga.beans;

public enum View {
    PERSONAL("personal"), PRODUCT_CREATE("productCreate");
    private String name;

    private View(String name) {
        this.name = name;
    }

    public String page() {
        return name;
    }

}
