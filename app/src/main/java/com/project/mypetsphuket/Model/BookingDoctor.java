package com.project.mypetsphuket.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingDoctor implements Parcelable {
      private String url , DoctorId , name , username , password;
      private Long ratingTime;
      private Double rating;

    public BookingDoctor() {
    }


    public BookingDoctor(String url, String doctorId, String name, String username, String password, Long ratingTime, Double rating) {
        this.url = url;
        DoctorId = doctorId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.ratingTime = ratingTime;
        this.rating = rating;
    }

    protected BookingDoctor(Parcel in) {
        url = in.readString();
        DoctorId = in.readString();
        name = in.readString();
        username = in.readString();
        password = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
            ratingTime = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(DoctorId);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(password);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ratingTime);
            dest.writeDouble(rating);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookingDoctor> CREATOR = new Creator<BookingDoctor>() {
        @Override
        public BookingDoctor createFromParcel(Parcel in) {
            return new BookingDoctor(in);
        }

        @Override
        public BookingDoctor[] newArray(int size) {
            return new BookingDoctor[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRatingTime() {
        return ratingTime;
    }

    public void setRatingTime(Long ratingTime) {
        this.ratingTime = ratingTime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
