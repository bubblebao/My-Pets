package com.project.mypetsphuket.BookingFragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.mypetsphuket.Model.BookingInformation;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class BookingStep4Fragment extends Fragment {

    SimpleDateFormat simpleDataFormat;
    LocalBroadcastManager localBroadcastManager;
    Unbinder unbinder;


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

    @OnClick(R.id.book_comfirm_button)
    void confirmBooking(){

        //Create book information
        BookingInformation bookingInformation = new BookingInformation();

        bookingInformation.setDoctorId(Prevalent.currentDoctor.getDoctorId());
        bookingInformation.setDoctorName(Prevalent.currentDoctor.getDoctorId());
        bookingInformation.setCustomerName(Prevalent.currentOnlineUser.getName());
        bookingInformation.setGetCustomerPhone(Prevalent.currentOnlineUser.getPhone());
        bookingInformation.setHospitalAddress(Prevalent.currentHospital.getAddress());
        bookingInformation.setHospitalId(Prevalent.currentHospital.getHospitalId());
        bookingInformation.setHospitalName(Prevalent.currentHospital.getName());
        bookingInformation.setTime(new StringBuilder(Prevalent.convertTimeSlotToString(Prevalent.currentTimeSlot)
        ).append(" at ").append(simpleDataFormat.format(Prevalent.currentDate.getTime())).toString());

        bookingInformation.setSlot(Long.valueOf(Prevalent.currentTimeSlot));

    //submit to Doctor document
        DocumentReference bookingData = FirebaseFirestore.getInstance()
                .collection("AllArea")
                .document(Prevalent.city)
                .collection("Branch")
                .document(Prevalent.currentHospital.getHospitalId())
                .collection("Doctors")
                .document(Prevalent.currentDoctor.getDoctorId())
                .collection(Prevalent.simpleDataFormat.format(Prevalent.currentDate.getTime()))
                .document(String.valueOf(Prevalent.currentTimeSlot));

        //Write data
        bookingData.set(bookingInformation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                resetStaticData();
                getActivity().finish();
                Toast.makeText(getContext(), "Booking Success!" ,Toast.LENGTH_SHORT ).show();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "!"+e.getMessage() ,Toast.LENGTH_SHORT ).show();
            }
        });


    }

    private void resetStaticData() {
        Prevalent.step = 0 ;
        Prevalent.currentTimeSlot = -1;
        Prevalent.currentHospital = null;
        Prevalent.currentDoctor = null;
        Prevalent.currentDate.add(Calendar.DATE,0);
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
        .append(simpleDataFormat.format(Prevalent.currentDate.getTime())));

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

      //  unbinder = ButterKnife.bind(this ,itemView);
        unbinder = ButterKnife.bind(this,itemView);

        return itemView;

    }
}

