package com.example.mluth.suapin.model;

import java.io.Serializable;

public class Donation implements Serializable {
    private String location,name,foodName,latitude,longitude,key;
    private int portion;

    public Donation(){}

    public Donation(String location, String name, String foodName, String latitude, String longitude, int portion) {
        this.location = location;
        this.name = name;
        this.foodName = foodName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.portion = portion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    @Override
    public String toString() {
        String data = " "+location+"\n"
                +" "+latitude+"\n"
                +" "+longitude+"\n"
                +" "+name+"\n"
                +" "+foodName+"\n"
                +" "+portion+"\n";
        return data;
    }
}
