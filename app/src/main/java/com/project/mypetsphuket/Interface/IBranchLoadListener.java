package com.project.mypetsphuket.Interface;

import com.project.mypetsphuket.Model.BookingHospitals;

import java.util.List;

public interface IBranchLoadListener {

    void onIBranchLoadSuccess(List<BookingHospitals> bookingHospitalsList);
    void onIBranchLoadFailed(String message);
}
