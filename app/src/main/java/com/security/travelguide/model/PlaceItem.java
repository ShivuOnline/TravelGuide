package com.security.travelguide.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public class PlaceItem {
    private String placeName;
    private String placeDescription;
    private String placeImageUrl;
    private Drawable placeImageDrawable;
    private double placeLatitude;
    private double placeLongitude;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getPlaceImageUrl() {
        return placeImageUrl;
    }

    public void setPlaceImageUrl(String placeImageUrl) {
        this.placeImageUrl = placeImageUrl;
    }

    public Drawable getPlaceImageDrawable() {
        return placeImageDrawable;
    }

    public void setPlaceImageDrawable(Drawable placeImageDrawable) {
        this.placeImageDrawable = placeImageDrawable;
    }

    public double getPlaceLatitude() {
        return placeLatitude;
    }

    public void setPlaceLatitude(double placeLatitude) {
        this.placeLatitude = placeLatitude;
    }

    public double getPlaceLongitude() {
        return placeLongitude;
    }

    public void setPlaceLongitude(double placeLongitude) {
        this.placeLongitude = placeLongitude;
    }

    @Override
    public String toString() {
        return "PlaceItem{" +
                "placeName='" + placeName + '\'' +
                ", placeDescription='" + placeDescription + '\'' +
                ", placeImageUrl='" + placeImageUrl + '\'' +
                ", placeImageDrawable=" + placeImageDrawable +
                ", placeLatitude=" + placeLatitude +
                ", placeLongitude=" + placeLongitude +
                '}';
    }
}
