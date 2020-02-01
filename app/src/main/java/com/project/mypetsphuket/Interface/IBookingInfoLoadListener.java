package com.project.mypetsphuket.Interface;

import com.project.mypetsphuket.Model.BookingInformation;

public interface IBookingInfoLoadListener {


    void onBookingInfoEmpty();
    void onBookingInfoLoadSuccess(BookingInformation bookingInformation);
    void onBookingInfoLoadFailed(String message);

}
