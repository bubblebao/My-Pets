package com.project.mypetsphuket.Model;

public class DoctorAndHospital {

    public DoctorAndHospital() {}

    private String id, name, phone, certificate, servicetype, url, address , working, location, locationlatitude, locationlongtitude, servicetime, specialist;

    public DoctorAndHospital(String id, String name, String phone, String certificate, String servicetype, String url, String address, String working, String location, String locationlatitude, String locationlongtitude, String servicetime, String specialist) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.certificate = certificate;
        this.servicetype = servicetype;
        this.url = url;
        this.address = address;
        this.working = working;
        this.location = location;
        this.locationlatitude = locationlatitude;
        this.locationlongtitude = locationlongtitude;
        this.servicetime = servicetime;
        this.specialist = specialist;
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

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
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
}

