package com.project.mypetsphuket.Prevalent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.mypetsphuket.HomeActivity;
import com.project.mypetsphuket.Model.BookingDoctor;
import com.project.mypetsphuket.Model.BookingHospitals;
import com.project.mypetsphuket.Model.BookingInformation;
import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.Model.HospitalId;
import com.project.mypetsphuket.Model.Rating;
import com.project.mypetsphuket.Model.TimeSlot;
import com.project.mypetsphuket.Model.Users;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.Spare.Products;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

import static android.content.ContentValues.TAG;

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

    public static final String RATING_INFORMATION_KEY = "RATING_INFORMATION";



    public  static SimpleDateFormat simpleDataFormat = new SimpleDateFormat("dd_MM_yyyy");

    public static int step = 0;
    public static final int TIME_SLOT_TOTAL = 20;
    public static Users currentOnlineUser;
    public static Rating currentRating;
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

    public static void updateToken(String s) {

    }

    public static void showRatingDialog(Context context, String stateName, String hospitalID,
                                        String hospitalName, String doctorID) {

        ///AllArea/Kathu/Branch/8HBa8DEkdeSSR2t7Zoik/Doctors/EQhAvxG4mlNnJdxlC1gM
        DocumentReference doctorNeedRateRef = FirebaseFirestore.getInstance()
                .collection("AllArea")
                .document(stateName)
                .collection("Branch")
                .document(hospitalID)
                .collection("Doctors")
                .document(doctorID);

        doctorNeedRateRef.get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context ,""+e.getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()){

                    BookingDoctor doctorsRate = task.getResult().toObject(BookingDoctor.class);
                    doctorsRate.setDoctorId(task.getResult().getId());

                    //Create View for dialog
                    View view = LayoutInflater.from(context)
                            .inflate(R.layout.layout_rating_dialog , null);
                    //Widget
                    TextView txt_hospital_name = (TextView) view.findViewById(R.id.txt_hosital_name);
                    TextView txt_doctor_name = (TextView) view.findViewById(R.id.txt_doctor_name);
                    RatingBar rating_bar = (RatingBar) view.findViewById(R.id.Customer_rating);

                    //Set Info
                    txt_doctor_name.setText(doctorsRate.getName());
                    txt_hospital_name.setText(hospitalName);

                    //Create Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setView(view)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //If select OK , we will update
                                    //rating information to Firestore
                                    Double original_rating = doctorsRate.getRating();
                                    Long ratingTimes = doctorsRate.getRatingTime();
                                    float userRating = rating_bar.getRating();

                                    Double finalRating = (original_rating+userRating);

                                    //Update_data
                                    Map<String,Object> data_update = new HashMap<>();
                                    data_update.put("rating" , finalRating);
                                    data_update.put("ratingTime" , ++ratingTimes);

                                    doctorNeedRateRef.update(data_update)
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, "!"+e.getMessage() ,Toast.LENGTH_SHORT ).show();

                                                }
                                            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

                                                Toast.makeText(context, "Thank for rating !" + Prevalent.RATING_INFORMATION_KEY , Toast.LENGTH_SHORT).show();
                                                SetDoneRatingToUser(context);

                                                Paper.init(context);
                                                Paper.book().delete(Prevalent.RATING_INFORMATION_KEY);
                                            }
                                        }
                                    });



                                }
                            }).setNegativeButton("SKIP", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                   //Dimis Display
                                    dialogInterface.dismiss();
                                }
                            }).setNeutralButton("NEVER", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SetDoneRatingToUser(context);

                                    Paper.init(context);
                                    Paper.book().delete(Prevalent.RATING_INFORMATION_KEY);
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });



    }

    private static void SetDoneRatingToUser(Context context) {

        DocumentReference ratingRef;

        Rating updateDone = new Rating();
        updateDone.setDone(true);


        //User/+66822828303/Booking/03_05_2020
        ratingRef = FirebaseFirestore.getInstance()
                .collection("User")
                .document(Prevalent.currentOnlineUser.getPhone())
                .collection("Booking")
                .document(Prevalent.simpleDataFormat.format(Prevalent.bookingDate.getTime()));

        ratingRef.update("done" , true)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "Done is TRUE !" +Prevalent.currentRating.isDone() , Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "Done successfully updated!");
                        }else {
                            Toast.makeText(context, "Done is Not update!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}
