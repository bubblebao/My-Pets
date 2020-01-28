package com.project.mypetsphuket.Interface;

import com.project.mypetsphuket.Model.BookingHospitals;
import com.project.mypetsphuket.Model.DoctorAndHospital;

import java.util.List;

public interface IBranchLoadListener {

    void onIBranchLoadSuccess(List<BookingHospitals> hospitalList);
    void onIBranchLoadFailed(String message);
}
