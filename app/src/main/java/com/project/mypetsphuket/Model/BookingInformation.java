package com.project.mypetsphuket.Model;

import com.google.firebase.Timestamp;

public class BookingInformation {

    private  String cityBook , customerImg , customerName , customerPhone , time , doctorId,  doctorName , HospitalId , HospitalName , HospitalAddress;
    private Long slot;
    private Timestamp timestamp;
    private boolean done;

    public BookingInformation() {
    }

    public BookingInformation(String cityBook, String customerImg, String customerName, String customerPhone, String time, String doctorId, String doctorName, String hospitalId, String hospitalName, String hospitalAddress, Long slot, Timestamp timestamp, boolean done) {
        this.cityBook = cityBook;
        this.customerImg = customerImg;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.time = time;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        HospitalId = hospitalId;
        HospitalName = hospitalName;
        HospitalAddress = hospitalAddress;
        this.slot = slot;
        this.timestamp = timestamp;
        this.done = done;
    }

    public String getCityBook() {
        return cityBook;
    }

    public void setCityBook(String cityBook) {
        this.cityBook = cityBook;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String hospitalId) {
        HospitalId = hospitalId;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return HospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        HospitalAddress = hospitalAddress;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}


