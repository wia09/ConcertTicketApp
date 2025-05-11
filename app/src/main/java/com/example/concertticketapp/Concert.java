package com.example.concertticketapp;

import com.google.firebase.Timestamp;

public class Concert {
    private String name;
    private String location;
    private Timestamp date;
    private int price;
    private int capacity;
    private String image;

    public Concert() {
        // Firestore-hoz kötelező
    }

    public Concert(String name, String location, Timestamp date, int price, int capacity, String image) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.price = price;
        this.capacity = capacity;
        this.image = image;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public Timestamp getDate() { return date; }
    public int getPrice() { return price; }
    public int getCapacity() { return capacity; }
    public String getImage() { return image; }

    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(Timestamp date) { this.date = date; }
    public void setPrice(int price) { this.price = price; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setImage(String image) { this.image = image; }
}