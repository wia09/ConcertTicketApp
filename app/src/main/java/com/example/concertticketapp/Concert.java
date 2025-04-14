package com.example.concertticketapp;

public class Concert {
    private String title;
    private String location;
    private String date;
    private int imageResId;

    public Concert(String title, String location, String date, int imageResId) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getImageResId() {
        return imageResId;
    }
}