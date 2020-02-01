package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.Empty;
import com.project.mypetsphuket.Interface.IBookingInfoLoadListener;
import com.project.mypetsphuket.Model.BookingInformation;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyAppointmentsActivity extends AppCompatActivity   {
    private TextView   closeTextBtn , NextTextButton;

    private Unbinder unbinder;

    TextView txt__hospital_address;
    TextView txt_hospital_doctor;
    TextView txt_time;
    TextView txt_time_remain;
    CardView card_booking_info;
    TextView txt_not_appoinment;


    IBookingInfoLoadListener iBookingInfoLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_appointments);

        closeTextBtn = (TextView) findViewById(R.id.close_my_appointment_btn);


        txt__hospital_address = (TextView) findViewById(R.id.txt__hospital_address);
        txt_hospital_doctor = (TextView) findViewById(R.id.txt_hospital_doctor);
        txt_time = (TextView) findViewById(R.id.txt_time);
        txt_time_remain = (TextView) findViewById(R.id.txt_time_remain);
        card_booking_info = (CardView) findViewById(R.id.card_booking_info);

        txt_not_appoinment = (TextView) findViewById(R.id.not_have_appointment);

        card_booking_info.setVisibility(View.GONE);
        txt_not_appoinment.setVisibility(View.VISIBLE);

        loadUserBooking();
    //    BookingInfoLoadDone();

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });


    }



    private void loadUserBooking() {

        CollectionReference userBooking = FirebaseFirestore.getInstance()
                .collection("User")
                .document(Prevalent.currentOnlineUser.getPhone())
                .collection("Booking");

        //Get Current date
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);

        Timestamp toDayTimeStamp = new Timestamp(calendar.getTime());

        //Select booking information form firebase

        userBooking
                .whereGreaterThanOrEqualTo("timestamp",toDayTimeStamp)
                .whereEqualTo("done",false)
                .limit(1)//Only 1
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    if (!task.getResult().isEmpty()){

                        for (QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()){

                            BookingInformation bookingInformation = queryDocumentSnapshot.toObject(BookingInformation.class);
                   //         iBookingInfoLoadListener.onBookingInfoLoadSuccess(bookingInformation);

                            BookingInfoLoadDone(bookingInformation);
                            Toast.makeText(MyAppointmentsActivity.this,"Success",Toast.LENGTH_SHORT).show();

                            break;
                        }
                    }




                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                 Toast.makeText(MyAppointmentsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                txt_not_appoinment.setVisibility(View.VISIBLE);
            }
        });


    }

    private void BookingInfoLoadDone(BookingInformation bookingInformation) {

        if (bookingInformation!= null){

            txt__hospital_address.setText(bookingInformation.getHospitalName());
            txt_hospital_doctor.setText(bookingInformation.getDoctorName());
            txt_time.setText(bookingInformation.getTime());
            String dateRemain = DateUtils.getRelativeTimeSpanString(
                    Long.valueOf(bookingInformation.getTimestamp().toDate().getTime()),
                    Calendar.getInstance().getTimeInMillis(),0 ).toString();
            txt_time_remain.setText(dateRemain);

            txt_not_appoinment.setVisibility(View.GONE);
            card_booking_info.setVisibility(View.VISIBLE);
        }




    }


}
