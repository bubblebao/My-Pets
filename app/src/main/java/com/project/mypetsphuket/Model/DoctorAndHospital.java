package com.project.mypetsphuket.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorAndHospital  implements Parcelable {

    public DoctorAndHospital() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationlatitude() {
        return locationlatitude;
    }

    public void setLocationlatitude(String locationlatitude) {
        this.locationlatitude = locationlatitude;
    }

    public String getLocationlongtitude() {
        return locationlongtitude;
    }

    public void setLocationlongtitude(String locationlongtitude) {
        this.locationlongtitude = locationlongtitude;
    }

    public String getServicetime() {
        return servicetime;
    }

    public void setServicetime(String servicetime) {
        this.servicetime = servicetime;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public static Creator<DoctorAndHospital> getCREATOR() {
        return CREATOR;
    }

    private String id, name, phone , servicetype, url, address, working, location, locationlatitude, locationlongtitude, servicetime, specialist;
    private Long rating;
    protected DoctorAndHospital(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        rating = in.readLong();
        servicetype = in.readString();
        url = in.readString();
        address = in.readString();
        working = in.readString();
        location = in.readString();
        locationlatitude = in.readString();
        locationlongtitude = in.readString();
        servicetime = in.readString();
        specialist = in.readString();
    }

    public static final Creator<DoctorAndHospital> CREATOR = new Creator<DoctorAndHospital>() {
        @Override
        public DoctorAndHospital createFromParcel(Parcel in) {
            return new DoctorAndHospital(in);
        }

        @Override
        public DoctorAndHospital[] newArray(int size) {
            return new DoctorAndHospital[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeLong(rating);
        dest.writeString(servicetype);
        dest.writeString(url);
        dest.writeString(address);
        dest.writeString(working);
        dest.writeString(location);
        dest.writeString(locationlatitude);
        dest.writeString(locationlongtitude);
        dest.writeString(servicetime);
        dest.writeString(specialist);
    }
}