package com.example.applicationrestaurant.Entities;

import java.util.UUID;

public class Cervezas {
    private String id;
    private String name;
    private String contentAlcohol;
    private int price;


    public Cervezas(String id, String name, String contentAlcohol, int price) {
        this.id = id;
        this.name = name;
        this.contentAlcohol = contentAlcohol;
        this.price = price;
    }

    public Cervezas(String name, String contentAlcohol, int price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.contentAlcohol = contentAlcohol;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContentAlcohol() {
        return contentAlcohol;
    }

    public void setContentAlcohol(String contentAlcohol) {
        this.contentAlcohol = contentAlcohol;
    }
}
