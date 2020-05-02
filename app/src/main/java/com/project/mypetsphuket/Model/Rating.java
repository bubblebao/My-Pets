package com.project.mypetsphuket.Model;

public class Rating {

    private String ratingState , hospitalID,  hospitalName ,doctorID, customerPhone, price;
    private boolean done;


    public Rating() {
    }

    public Rating(String ratingState, String hospitalID, String hospitalName, String doctorID, String customerPhone, String price, boolean done) {
        this.ratingState = ratingState;
        this.hospitalID = hospitalID;
        this.hospitalName = hospitalName;
        this.doctorID = doctorID;
        this.customerPhone = customerPhone;
        this.price = price;
        this.done = done;
    }

    public String getRatingState() {
        return ratingState;
    }

    public void setRatingState(String ratingState) {
        this.ratingState = ratingState;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
