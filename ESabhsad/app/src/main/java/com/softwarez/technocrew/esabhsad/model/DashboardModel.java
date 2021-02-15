package com.softwarez.technocrew.esabhsad.model;
public class DashboardModel {
    int images;
    String title,number;

    public DashboardModel(int images, String title, String number) {
        this.images = images;
        this.title = title;
        this.number = number;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
