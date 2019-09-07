package com.project.mypetsphuket.Model;

public class Doctors {

    String Name, Phone , Working , Location, Locationlatitude, Locationlongtitude,Servicetime, Specialist ;

    public String getServicetime() {
        return Servicetime;
    }

    public Doctors()
    {

    }




    public void setServicetime(String servicetime) {
        Servicetime = servicetime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getWorking() {
        return Working;
    }

    public void setWorking(String working) {
        Working = working;
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

    public String getSpecialist() {
        return Specialist;
    }

    public void setSpecialist(String specialist) {
        Specialist = specialist;
    }
}
