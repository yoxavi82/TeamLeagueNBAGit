package com.example.teamleaguebagit;

import android.graphics.drawable.Drawable;

public class PlayerModel {
    private String name;
    private Drawable image_drawable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(Drawable image_drawable) {
        this.image_drawable = image_drawable;
    }
}
