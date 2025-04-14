package com.example.concertticketapp;

public class Concert {
    private String title;
    private String location;
    private String date;

    public Concert() {}

    public Concert(String title, String location, String date) {
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getDate() { return date; }

    public void setTitle(String title) { this.title = title; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(String date) { this.date = date; }
}