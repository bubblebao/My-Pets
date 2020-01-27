package com.project.mypetsphuket.Prevalent;

import android.os.Parcelable;

import com.google.firebase.firestore.auth.User;
import com.project.mypetsphuket.Model.DoctorAndHospital;
import com.project.mypetsphuket.Model.Users;

import java.util.ArrayList;

public class Prevalent {

    public static final String KEY_SNAPSHOT_LOAD_DONE = "SNAPSHOT_DONE";
    public static Users currentOnlineUser;


    public static final String UserPhoneKey = "UserPhone";
    public static final String UserEmailKey = "UserEmail";
    public static final String UserPasswordKey = "UserPassword";
    public static final String parentDbNameKey = "parentDbName";

    public static int step = 0;
    public static final String KEY_ENABLE_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_ITEM_STORE = "ITEM_SAVE" ;

    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT" ;
    public static final String KEY_STEP = "STEP";

    public static DoctorAndHospital currentSelect;
    public static String Select;
}
