package com.rapidzz.hospital_management.models;

public class HomeModel {
    private String name;
    private int imageId;

    public HomeModel(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
