package com.project.mypetsphuket.Model;

public class Hospitals {

   // Name, Description, Phone, Location, Locationlatitude, Locationlongtitude, Servicetype
    private String image , url , name, description, phone, location, locationlatitude, locationlongtitude, servicetype , srvicetime , rating ;


    public Hospitals() {}

    public Hospitals(String image, String url, String name, String description, String phone, String location, String locationlatitude, String locationlongtitude, String servicetype, String srvicetime, String rating) {
        this.image = image;
        this.url = url;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.location = location;
        this.locationlatitude = locationlatitude;
        this.locationlongtitude = locationlongtitude;
        this.servicetype = servicetype;
        this.srvicetime = srvicetime;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSrvicetime() {
        return srvicetime;
    }

    public void setSrvicetime(String srvicetime) {
        this.srvicetime = srvicetime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
