package com.project.mypetsphuket.Interface;

import com.project.mypetsphuket.Model.TimeSlot;

import java.util.List;

public interface ITimeSlotLoadListener {

    void onTimeSlotLoadSucess(List<TimeSlot> timeSlotList);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotEmpty();
}
