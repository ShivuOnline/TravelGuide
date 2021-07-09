package com.security.travelguide.model.galleryDetails;

import android.os.Parcel;
import android.os.Parcelable;

public class GalleryUploadMain implements Parcelable {
    private String placePhotoId;
    private String placePhotoPath;
    private String placePhotoUploadedDate;
    private String userComments;
    private double latitude;
    private double longitude;

    public GalleryUploadMain() {
    }

    protected GalleryUploadMain(Parcel in) {
        placePhotoId = in.readString();
        placePhotoPath = in.readString();
        placePhotoUploadedDate = in.readString();
        userComments = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<GalleryUploadMain> CREATOR = new Creator<GalleryUploadMain>() {
        @Override
        public GalleryUploadMain createFromParcel(Parcel in) {
            return new GalleryUploadMain(in);
        }

        @Override
        public GalleryUploadMain[] newArray(int size) {
            return new GalleryUploadMain[size];
        }
    };

    public String getPlacePhotoId() {
        return placePhotoId;
    }

    public void setPlacePhotoId(String placePhotoId) {
        this.placePhotoId = placePhotoId;
    }

    public String getPlacePhotoPath() {
        return placePhotoPath;
    }

    public void setPlacePhotoPath(String placePhotoPath) {
        this.placePhotoPath = placePhotoPath;
    }

    public String getPlacePhotoUploadedDate() {
        return placePhotoUploadedDate;
    }

    public void setPlacePhotoUploadedDate(String placePhotoUploadedDate) {
        this.placePhotoUploadedDate = placePhotoUploadedDate;
    }

    public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GalleryUploadMain{" +
                "placePhotoId='" + placePhotoId + '\'' +
                ", placePhotoPath='" + placePhotoPath + '\'' +
                ", placePhotoUploadedDate='" + placePhotoUploadedDate + '\'' +
                ", userComments='" + userComments + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(placePhotoId);
        parcel.writeString(placePhotoPath);
        parcel.writeString(placePhotoUploadedDate);
        parcel.writeString(userComments);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}
