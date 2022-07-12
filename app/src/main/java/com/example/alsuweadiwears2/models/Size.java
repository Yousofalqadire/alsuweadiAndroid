package com.example.alsuweadiwears2.models;

public class Size {
    private int id;
    private String value;
    private int productId;
    private int quantity;

    public Size(int id, String value, int productId, int quantity) {
        this.id = id;
        this.value = value;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
