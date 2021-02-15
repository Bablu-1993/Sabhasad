package com.softwarez.technocrew.esabhsad.model;

public class AdminModel {
    String title;
    int image,arrow;

    public AdminModel(String title, int image, int arrow) {
        this.title = title;
        this.image = image;
        this.arrow = arrow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getArrow() {
        return arrow;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }
}
