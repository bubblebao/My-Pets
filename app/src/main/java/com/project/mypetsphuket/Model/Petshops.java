package com.project.mypetsphuket.Model;

public class Petshops {

    public Petshops(String name, String description, String phone, String location, String locationlatitude, String locationlongtitude, String servicetype, String servicetime) {
        Name = name;
        Description = description;
        Phone = phone;
        Location = location;
        Locationlatitude = locationlatitude;
        Locationlongtitude = locationlongtitude;
        Servicetype = servicetype;
        Servicetime = servicetime;

    }


    public Petshops()
    {

    }

    String Image , Name, Description, Phone, Location, Locationlatitude, Locationlongtitude, Servicetype , Servicetime ;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Petshops(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getLocationlatitude() {
        return Locationlatitude;
    }

    public void setLocationlatitude(String locationlatitude) {
        Locationlatitude = locationlatitude;
    }

    public String getLocationlongtitude() {
        return Locationlongtitude;
    }

    public void setLocationlongtitude(String locationlongtitude) {
        Locationlongtitude = locationlongtitude;
    }

    public String getServicetype() {
        return Servicetype;
    }

    public void setServicetype(String servicetype) {
        Servicetype = servicetype;
    }

    public String getServicetime() {
        return Servicetime;
    }

    public void setServicetime(String servicetime) {
        Servicetime = servicetime;
    }

}
