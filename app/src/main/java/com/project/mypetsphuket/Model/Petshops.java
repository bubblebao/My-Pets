package com.project.mypetsphuket.Model;

public class Petshops {

    private String image , name, description, phone, location, locationlatitude, locationlongtitude, servicetype , servicetime ;

    public Petshops() { }


    public Petshops(String image, String name, String description, String phone, String location, String locationlatitude, String locationlongtitude, String servicetype, String servicetime) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.location = location;
        this.locationlatitude = locationlatitude;
        this.locationlongtitude = locationlongtitude;
        this.servicetype = servicetype;
        this.servicetime = servicetime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getServicetime() {
        return servicetime;
    }

    public void setServicetime(String servicetime) {
        this.servicetime = servicetime;
    }
}
