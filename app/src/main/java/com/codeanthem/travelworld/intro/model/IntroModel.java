package com.codeanthem.travelworld.intro.model;

import java.io.Serializable;

public class IntroModel  implements Serializable {

    private int id;
    private String title;
    private String description;
    private int imageId;

    public IntroModel(int id, String title, String description, int imageId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
