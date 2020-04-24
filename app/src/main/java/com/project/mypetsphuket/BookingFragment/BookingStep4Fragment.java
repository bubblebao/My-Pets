package com.project.mypetsphuket.BookingFragment;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.mypetsphuket.Model.BookingInformation;

import com.project.mypetsphuket.Model.Mynotification;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;


public class BookingStep4Fragment extends Fragment {

    SimpleDateFormat simpleDataFormat;
    LocalBroadcastManager localBroadcastManager;
    Unbinder unbinder;

    AlertDialog dialog;

    @BindView(R.id.txt_confirm_doctor_time)
    TextView txt_confirm_doctor_time;
    @BindView(R.id.txt_confirm_doctor_name)
    TextView txt_confirm_doctor_name;
    @BindView(R.id.txt_confirm_hospital_name)
    TextView txt_confirm_hospital_name;
    @BindView(R.id.txt_confirm_hospital_phone)
    TextView txt_confirm_hospital_phone;
    @BindView(R.id.txt_confirm_hospital_servicetime)
    TextView txt_confirm_open_hours;
    @BindView(R.id.txt_confirm_hospital_address)
    TextView txt_confirm_hospital_address;
    @BindView(R.id.bookingProgressBar)
    ProgressBar booking_progressBar;
    @BindView(R.id.book_comfirm_button)
    Button book_comfirm_button;

    @OnClick(R.id.book_comfirm_button)
    void confirmBooking(){

        dialog.show();

        book_comfirm_button.setVisibility(View.INVISIBLE);
        booking_progressBar.setVisibility(View.VISIBLE);


        //Process Timestamp
        String startTime = Prevalent.convertTimeSlotToString(Prevalent.currentTimeSlot);
        String [] convertTime = startTime.split("-");
        //start time 9:00
        String[] startTimeConvert = convertTime[0].split(":");
        int startHourInt = Integer.parseInt(startTimeConvert[0].trim());
        int startMinInt = Integer.parseInt(startTimeConvert[1].trim());

        Calendar bookingDateWithourHouse = Calendar.getInstance();
        bookingDateWithourHouse.setTimeInMillis(Prevalent.bookingDate.getTimeInMillis());
        bookingDateWithourHouse.set(Calendar.HOUR_OF_DAY,startHourInt);
        bookingDateWithourHouse.set(Calendar.MINUTE,startMinInt);

        Timestamp timestamp = new Timestamp(bookingDateWithourHouse.getTime());


        //Create book information
        BookingInformation bookingInformation = new BookingInformation();

        bookingInformation.setDone(false);


        bookingInformation.setTimestamp(timestamp);

        bookingInformation.setCityBook(Prevalent.city);
        bookingInformation.setDoctorId(Prevalent.currentDoctor.getDoctorId());
        bookingInformation.setDoctorName(Prevalent.currentDoctor.getName());
        bookingInformation.setCustomerName(Prevalent.currentOnlineUser.getName());
        bookingInformation.setCustomerPhone(Prevalent.currentOnlineUser.getPhone());
        bookingInformation.setCustomerImg(Prevalent.currentOnlineUser.getUrl());
        bookingInformation.setHospitalAddress(Prevalent.currentHospital.getAddress());
        bookingInformation.setHospitalId(Prevalent.currentHospital.getHospitalId());
        bookingInformation.setHospitalName(Prevalent.currentHospital.getName());
        bookingInformation.setTime(new StringBuilder(Prevalent.convertTimeSlotToString(Prevalent.currentTimeSlot)
        ).append(" at ").append(simpleDataFormat.format(bookingDateWithourHouse.getTime())).toString());
        bookingInformation.setSlot(Long.valueOf(Prevalent.currentTimeSlot));

       //submit to Doctor document
        DocumentReference bookingData = FirebaseFirestore.getInstance()
                .collection("AllArea")
                .document(Prevalent.city)
                .collection("Branch")
                .document(Prevalent.currentHospital.getHospitalId())
                .collection("Doctors")
                .document(Prevalent.currentDoctor.getDoctorId())
                .collection(Prevalent.simpleDataFormat.format(Prevalent.bookingDate.getTime()))
                .document(String.valueOf(Prevalent.currentTimeSlot));

        //Write data
        bookingData.set(bookingInformation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Function check Prevalent new book
                addToUserBooking(bookingInformation);

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "!"+e.getMessage() ,Toast.LENGTH_SHORT ).show();
            }
        });


    }

    private void addToUserBooking(final BookingInformation bookingInformation) {

        //Frist Create new Collection
      final    CollectionReference userBooking = FirebaseFirestore.getInstance()
                .collection("User")
                .document(Prevalent.currentOnlineUser.getPhone())
                .collection("Booking");

        //Get Current date
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);

        Timestamp toDayTimeStamp = new Timestamp(calendar.getTime());


        userBooking
                .whereGreaterThanOrEqualTo("timestamp",toDayTimeStamp)
                .whereEqualTo("done",false)
                .limit(1)//Only 1
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.getResult().isEmpty()){
                            userBooking.document()
                                    .set(bookingInformation)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {


                                        @Override
                                        public void onSuccess(Void aVoid) {


                                            Mynotification mynotification = new Mynotification();
                                            mynotification.setUid(UUID.randomUUID().toString());
                                            mynotification.setTitle("New Booking");
                                            mynotification.setContent("You have a new appointment from My Pet!");
                                            mynotification.setRead(false);

                                            FirebaseFirestore.getInstance()
                                                    .collection("AllArea")
                                                    .document(Prevalent.city)
                                                    .collection("Branch")
                                                    .document(Prevalent.currentHospital.getHospitalId())
                                                    .collection("Doctors")
                                                    .document(Prevalent.currentDoctor.getDoctorId())
                                                    .collection("Notifications")
                                                    .document(mynotification.getUid())
                                                    .set(mynotification)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {


                                                            book_comfirm_button.setVisibility(View.VISIBLE);
                                                            booking_progressBar.setVisibility(View.INVISIBLE);
                                                            dialog.dismiss();

                                                            resetStaticData();
                                                            getActivity().finish();


                                                            Toast.makeText(getContext(), "Booking Success!" ,Toast.LENGTH_SHORT ).show();



                                                        }
                                                    });




                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (dialog.isShowing())
                                        dialog.dismiss();
                                    book_comfirm_button.setVisibility(View.VISIBLE);
                                    booking_progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), e.getMessage() ,Toast.LENGTH_SHORT ).show();
                                }
                            });
                        }else {
                            if (dialog.isShowing())
                                dialog.dismiss();

                            resetStaticData();
                            getActivity().finish();
                            Toast.makeText(getContext(), "Booking Success!" ,Toast.LENGTH_SHORT ).show();
                            book_comfirm_button.setVisibility(View.VISIBLE);
                            booking_progressBar.setVisibility(View.INVISIBLE);

                        }

                    }
                });
    }

    private void addToCalendar(Calendar bookingDate, String startDate) {

        //Process Timestamp
        String startTime = Prevalent.convertTimeSlotToString(Prevalent.currentTimeSlot);
        String [] convertTime = startTime.split("-");
        //start time 9:00
        String[] startTimeConvert = convertTime[0].split(":"); //get 9
        int startHourInt = Integer.parseInt(startTimeConvert[0].trim()); //get 00
        int startMinInt = Integer.parseInt(startTimeConvert[1].trim());

        String[] endTimeConvert = convertTime[1].split(":");
        int endHourInt = Integer.parseInt(endTimeConvert[0].trim());
        int endMinInt = Integer.parseInt(endTimeConvert[1].trim());

        Calendar startEvent = Calendar.getInstance();
        startEvent.setTimeInMillis(bookingDate.getTimeInMillis());
        startEvent.set(Calendar.HOUR_OF_DAY,startHourInt);
        startEvent.set(Calendar.MINUTE,startMinInt);

        Calendar endEvent = Calendar.getInstance();
        endEvent.setTimeInMillis(bookingDate.getTimeInMillis());
        endEvent.set(Calendar.HOUR_OF_DAY,endHourInt);
        endEvent.set(Calendar.MINUTE,endMinInt);

        //

        SimpleDateFormat calendarDataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String startEventTime = calendarDataFormat.format(startEvent.getTime());
        String endEventTime = calendarDataFormat.format(endEvent.getTime());

        addToDeviceCalendar(startEventTime , endEventTime , "My Pets Booking" ,
                new StringBuilder("Booking from")
        .append(startTime)
        .append(" with ")
        .append(Prevalent.currentDoctor.getName())
        .append(" at ")
        .append(Prevalent.currentHospital.getName()).toString(),
                new StringBuilder("Address: ")
        .append(Prevalent.currentHospital.getAddress()).toString());

    }

    private void addToDeviceCalendar(String startEventTime, String endEventTime, String title, String description, String location) {
        SimpleDateFormat calendarFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date start = calendarFormat.parse(startEventTime);
            Date end = calendarFormat.parse(endEventTime);

            ContentValues event = new ContentValues();

            //Put
            event.put(CalendarContract.Events.CALENDAR_ID , getCalendar(getContext()));
            event.put(CalendarContract.Events.TITLE ,title);
            event.put(CalendarContract.Events.DESCRIPTION ,description);
            event.put(CalendarContract.Events.EVENT_LOCATION ,location);

            //Time
            event.put(CalendarContract.Events.DTSTART ,start.getTime());
            event.put(CalendarContract.Events.DTEND ,end.getTime());
            event.put(CalendarContract.Events.ALL_DAY ,0);
            event.put(CalendarContract.Events.HAS_ALARM ,1);

            String timeZone = TimeZone.getDefault().getID();
            event.put(CalendarContract.Events.EVENT_TIMEZONE,timeZone);

            Uri calendars;

            if (Build.VERSION.SDK_INT >= 24)
                calendars = Uri.parse("content://com.android.calendar/events");
            else
                calendars = Uri.parse("content://calendar/events" );

            getActivity().getContentResolver().insert(calendars,event);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private String getCalendar(Context context) {

        String gmailCalendar = "";
        String projecttion [] = {"_id" , "calendar_displayName"};
        Uri calendars = Uri.parse("content://com.android.calendar/calendars");
        Uri calendarUri;
        Uri eventUri;

        if (Build.VERSION.SDK_INT >= 24)
            calendars = Uri.parse("content://com.android.calendar/calendars");
        else
            calendars = Uri.parse("content://calendar/calendars" );



        //calendars

        ContentResolver contentResolver = context.getContentResolver();
        //Select All calendars
        Cursor managedCursor = contentResolver.query( calendars ,projecttion , null , null , null);

        if (managedCursor.moveToFirst()){

            String calName;
                    int nameCol = managedCursor.getColumnIndex(projecttion[1]);
                    int idCol = managedCursor.getColumnIndex(projecttion[0]);
                    do {
                        calName = managedCursor.getString(nameCol);
                        if (calName.contains("@gmail.com"))
                            gmailCalendar = managedCursor.getString(idCol);
                            break;
                    }while (managedCursor.moveToNext());
                    managedCursor.close();
        }


        return gmailCalendar;
    }


    private void resetStaticData() {
        Prevalent.step = 0 ;
        Prevalent.currentTimeSlot = -1;
        Prevalent.currentHospital = null;
        Prevalent.currentDoctor = null;
        Prevalent.bookingDate.add(Calendar.DATE,0);
    }

    private BroadcastReceiver confirmBookingRececeiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setData();
        }
    };

    private void setData() {
        txt_confirm_doctor_name.setText(Prevalent.currentDoctor.getName());
        txt_confirm_doctor_time.setText(new StringBuilder(Prevalent.convertTimeSlotToString(Prevalent.currentTimeSlot)
        ).append(" at ")
        .append(simpleDataFormat.format(Prevalent.bookingDate.getTime())));

        txt_confirm_hospital_name.setText(Prevalent.currentHospital.getName());
        txt_confirm_hospital_phone.setText(Prevalent.currentHospital.getPhone());
        txt_confirm_open_hours.setText(Prevalent.currentHospital.getOpenHours());
        txt_confirm_hospital_address.setText(Prevalent.currentHospital.getAddress());

    }

    static BookingStep4Fragment instance;

    public static BookingStep4Fragment getInstance(){
        if (instance == null)
            instance = new BookingStep4Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Display Confirm
        simpleDataFormat = new SimpleDateFormat("dd/MM/yyyy");
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(confirmBookingRececeiver , new IntentFilter(Prevalent.KEY_COMFIRM_BOOKING));

        dialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false)
                .build();
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(confirmBookingRececeiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step4,container,false);

        unbinder = ButterKnife.bind(this,itemView);

        return itemView;

    }
}

