package com.example.alsuweadiwears2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Product implements Parcelable {
    private int id;
    private String name;
    private String category;
    private String brand;
    private double price;
    private String details;
    private Photo photo;
    private List<Size> sizes;
    private boolean isLiked;

    public Product(int id, String name, String category, String brand,
                   double price, String details, Photo photo, List<Size> sizes,boolean isLiked) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.details = details;
        this.photo = photo;
        this.sizes = sizes;
        this.isLiked = isLiked;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readString();
        brand = in.readString();
        price = in.readDouble();
        details = in.readString();
    }

    public Product(int id, boolean isLiked) {
        this.id = id;
        this.isLiked = isLiked;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", details='" + details + '\'' +
                ", photo=" + photo +
                ", sizes=" + sizes +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(brand);
        dest.writeDouble(price);
        dest.writeString(details);
    }
}
