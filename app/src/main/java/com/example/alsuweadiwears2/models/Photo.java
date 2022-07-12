package com.example.alsuweadiwears2.models;

public class Photo {
    private int id;
    private String url;
    private String publicId;
    private int productId;

    public Photo(int id, String url, String publicId, int productId) {
        this.id = id;
        this.url = url;
        this.publicId = publicId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
