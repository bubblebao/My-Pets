package com.project.mypetsphuket.Model;

public class HospitalId {
    private String HospitalId , name;

    public HospitalId(String hospitalId, String name) {
        HospitalId = hospitalId;
        this.name = name;
    }


    public HospitalId() {
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String hospitalId) {
        HospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
