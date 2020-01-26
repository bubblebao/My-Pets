package com.project.mypetsphuket.Interface;

import com.project.mypetsphuket.Model.DoctorAndHospital;

import java.util.List;

public interface IBranchLoadListener {

    void onIBranchLoadSuccess(List<DoctorAndHospital> NameList);
    void onIBranchLoadFailed(String message);
}
