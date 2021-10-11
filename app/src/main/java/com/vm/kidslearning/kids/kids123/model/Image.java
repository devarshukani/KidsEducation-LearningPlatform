package com.vm.kidslearning.kids.kids123.model;

import java.io.Serializable;

/**
 * Created by VISHAL on 9/14/2017.
 */

public class Image implements Serializable {
    private String name;
    private String ImageID;
    private String ImageName;
    private String ImageURL;

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    private String ImageCategoryName;
    private String small, medium, large;
    private String timestamp;


    public Image() {
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getImageCategoryName() {
        return ImageCategoryName;
    }

    public void setImageCategoryName(String imageCategoryName) {
        ImageCategoryName = imageCategoryName;
    }

    public Image(String name, String ImageName, String small, String medium, String large, String timestamp) {
        this.name = name;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.timestamp = timestamp;

        this.ImageName=ImageName;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String ImageName) {
        this.ImageName = ImageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

