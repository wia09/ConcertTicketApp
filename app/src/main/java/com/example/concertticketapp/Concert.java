package com.example.concertticketapp;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Concert implements Serializable {
    private String id;
    private String name;
    private String location;
    private Timestamp date;
    private int price;
    private int capacity;
    private String image;
    private String description;

    public Concert() {
        // Firestore-hoz kötelező
    }

    public Concert(String id, String name, String location, Timestamp date, int price, int capacity, String image, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.price = price;
        this.capacity = capacity;
        this.image = image;
        this.description = description;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public Timestamp getDate() { return date; }
    public int getPrice() { return price; }
    public int getCapacity() { return capacity; }
    public String getImage() { return image; }
    public String getDescription() { return description; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(Timestamp date) { this.date = date; }
    public void setPrice(int price) { this.price = price; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setImage(String image) { this.image = image; }
    public void setDescription(String description) { this.description = description; }
}