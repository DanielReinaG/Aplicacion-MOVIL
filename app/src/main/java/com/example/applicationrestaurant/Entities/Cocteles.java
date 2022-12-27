package com.example.applicationrestaurant.Entities;

import java.util.UUID;

public class Cocteles {
    private String id;
    private String name;
    private String description;
    private int price;
    private String image;

    public Cocteles(String id, String name, String description, int price, String image) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public Cocteles(String name, String description, int price, String image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
