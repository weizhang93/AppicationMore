package com.example.kimo.daygo.model;

/**
 * Created by Administrator on 2015/12/25 0025.
 */
public class Record {
    private String title;
    private String location;

    public Record(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
