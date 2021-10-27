package com.example.seaside;

public class EventInfo {
    private String title;
    private String description;
    private String location;
    private String time;

    public EventInfo(String title, String description, String location, String time) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}

