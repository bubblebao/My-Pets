package com.project.mypetsphuket.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingHospitals implements Parcelable {
    private String hospitalId , url , name , address ;
    private Long  rating ;

    public BookingHospitals( ) {

    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    protected BookingHospitals(Parcel in) {
        hospitalId = in.readString();
        url = in.readString();
        name = in.readString();
        address = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readLong();
        }
    }

    public static final Creator<BookingHospitals> CREATOR = new Creator<BookingHospitals>() {
        @Override
        public BookingHospitals createFromParcel(Parcel in) {
            return new BookingHospitals(in);
        }

        @Override
        public BookingHospitals[] newArray(int size) {
            return new BookingHospitals[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hospitalId);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(address);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(rating);
        }
    }
}
