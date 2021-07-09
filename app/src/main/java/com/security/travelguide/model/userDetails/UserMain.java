package com.security.travelguide.model.userDetails;

import android.os.Parcel;
import android.os.Parcelable;

public class UserMain implements Parcelable {
    private String mobileNumber;
    private String mPin;
    private String userName;
    private String gender;
    private String role;
    private String isActive;
    private String profilePicUrl;

    public UserMain() {
    }

    protected UserMain(Parcel in) {
        mobileNumber = in.readString();
        mPin = in.readString();
        userName = in.readString();
        gender = in.readString();
        role = in.readString();
        isActive = in.readString();
        profilePicUrl = in.readString();
    }

    public static final Creator<UserMain> CREATOR = new Creator<UserMain>() {
        @Override
        public UserMain createFromParcel(Parcel in) {
            return new UserMain(in);
        }

        @Override
        public UserMain[] newArray(int size) {
            return new UserMain[size];
        }
    };

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getmPin() {
        return mPin;
    }

    public void setmPin(String mPin) {
        this.mPin = mPin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public String toString() {
        return "UserMain{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", mPin='" + mPin + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", isActive='" + isActive + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mobileNumber);
        parcel.writeString(mPin);
        parcel.writeString(userName);
        parcel.writeString(gender);
        parcel.writeString(role);
        parcel.writeString(isActive);
        parcel.writeString(profilePicUrl);
    }
}
