package com.project.mypetsphuket.Prevalent;

import com.google.firebase.Timestamp;
import com.project.mypetsphuket.Model.BookingDoctor;
import com.project.mypetsphuket.Model.BookingHospitals;
import com.project.mypetsphuket.Model.BookingInformation;
import com.project.mypetsphuket.Model.HospitalId;
import com.project.mypetsphuket.Model.TimeSlot;
import com.project.mypetsphuket.Model.Users;
import com.project.mypetsphuket.Spare.Products;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Prevalent {

    public static final String KEY_DOCTOR_SELECTED = "DOCTOR_SELECTED";
    public static final String KEY_COMFIRM_BOOKING = "COMFIRM_BOOKING";
//    public static final String KEY_TIME_SLOT = "TIME_SLOT";

    public static final String UserPhoneKey = "UserPhone";
    public static final String UserEmailKey = "UserEmail";
    public static final String UserPasswordKey = "UserPassword";
    public static final String parentDbNameKey = "parentDbName";


    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_HOSPITAL_STORE = "HOSPITAL_SAVE";

    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";
    public static final String KEY_STEP = "STEP";

    public static final String KEY_DOCTOR_LOAD_DONE = "DOCTOR_LOAD_DONE";
    public static final Object DISABLE_TAG = "DISABLE";
    public static final String KEY_TIME_SLOT = "TIME_SLOT";
    public static final String TITTLE_KEY = "TITTLE" ;
    public static final String CONTENT_KEY = "CONTENT";
    public static final String LOGGED_KEY = "UserLogged";
    public static  String IS_LODIN = "IsLogin";


    public  static SimpleDateFormat simpleDataFormat = new SimpleDateFormat("dd_MM_yyyy");

    public static int step = 0;
    public static final int TIME_SLOT_TOTAL = 20;
    public static Users currentOnlineUser;
    public static BookingHospitals currentHospital;
    public static BookingDoctor currentDoctor;
    public static int currentTimeSlot = -1;
    public static Calendar bookingDate = Calendar.getInstance();


    public static String city = "";

    public static BookingInformation currentBooking;
    public static String currentBookingID = "";


    public Prevalent() {
    }

    public static String convertTimeSlotToString(int slot) {

        switch (slot) {
            case 0:
                return "9:00 - 9:30";

            case 1:
                return "9:30 - 10:00";

            case 2:
                return "10:00 - 10:30";

            case 3:
                return "10:30 - 11:00";

            case 4:
                return "11:30 - 12:00";

            case 5:
                return "13:00 - 13:30";

            case 6:
                return "13:30 - 14:00";

            case 7:

                return "14:00 - 14:30";

            case 8:
                return "14:30 - 15:00";

            case 9:
                return "15:00 - 15:30";

            case 10:
                return "15:30 - 16:00";

            case 11:
                return "16:00 - 16:30";

            case 12:
                return "16:30 - 17:00";

            case 13:
                return "17:00 - 17:30";

            case 14:
                return "17:30 - 18:00";

            case 15:
                return "18:00 - 18:30";

            case 16:
                return "18:30 - 19:00";

            case 17:
                return "19:00 - 20:00";

            case 18:
                return "20:00 - 20:30";

            case 19:
                return "20:30 - 21:00";

            default:
                return "Closed";

        }
    }

    public static String convertTimeSlotToStringKey(Timestamp timestamp) {
            Date date = timestamp.toDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");
            return simpleDateFormat.format(date);


    }

    public static void updateToket(String s) {
    }
}
