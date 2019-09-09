package com.project.mypetsphuket.Model;

public class Doctors {

    private String name, phone , working , location, locationlatitude, locationlongtitude,servicetime, specialist ;

    public Doctors()
    {

    }


    public Doctors(String name, String phone, String working, String location, String locationlatitude, String locationlongtitude, String servicetime, String specialist) {
        this.name = name;
        this.phone = phone;
        this.working = working;
        this.location = location;
        this.locationlatitude = locationlatitude;
        this.locationlongtitude = locationlongtitude;
        this.servicetime = servicetime;
        this.specialist = specialist;
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
}
