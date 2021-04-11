package com.security.travelguide.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class PlaceItem implements Parcelable {
    private String placeName;
    private String placeDescription;
    private String placeImageUrl;
    private String staticPlaceType;
    private Drawable placeImageDrawable;
    private double placeLatitude;
    private double placeLongitude;

    public PlaceItem() {
    }

    protected PlaceItem(Parcel in) {
        placeName = in.readString();
        placeDescription = in.readString();
        placeImageUrl = in.readString();
        staticPlaceType = in.readString();
        placeLatitude = in.readDouble();
        placeLongitude = in.readDouble();
    }

    public static final Creator<PlaceItem> CREATOR = new Creator<PlaceItem>() {
        @Override
        public PlaceItem createFromParcel(Parcel in) {
            return new PlaceItem(in);
        }

        @Override
        public PlaceItem[] newArray(int size) {
            return new PlaceItem[size];
        }
    };

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

    public String getStaticPlaceType() {
        return staticPlaceType;
    }

    public void setStaticPlaceType(String staticPlaceType) {
        this.staticPlaceType = staticPlaceType;
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
                ", staticPlaceType='" + staticPlaceType + '\'' +
                ", placeImageDrawable=" + placeImageDrawable +
                ", placeLatitude=" + placeLatitude +
                ", placeLongitude=" + placeLongitude +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(placeName);
        parcel.writeString(placeDescription);
        parcel.writeString(placeImageUrl);
        parcel.writeString(staticPlaceType);
        parcel.writeDouble(placeLatitude);
        parcel.writeDouble(placeLongitude);
    }
}
