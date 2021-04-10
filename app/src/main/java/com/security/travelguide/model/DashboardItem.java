package com.security.travelguide.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public class DashboardItem {
    private String title;
    private String description;
    private Uri ImageUrl;
    private Drawable imageDrawable;

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

    public Uri getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    @Override
    public String toString() {
        return "DashboardItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ImageUrl=" + ImageUrl +
                ", imageDrawable=" + imageDrawable +
                '}';
    }
}
