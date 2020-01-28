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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.mypetsphuket.Interface.ITimeSlotLoadListener;
import com.project.mypetsphuket.Model.TimeSlot;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.MyTimeSlotAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep3Fragment extends Fragment implements ITimeSlotLoadListener {

    static BookingStep3Fragment instance;
    DocumentReference branchRef;
    ITimeSlotLoadListener iTimeSlotLoadListener;

    Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;
    Calendar select_date;
    String NamePlace;

    public static BookingStep3Fragment getInstance(){
        if (instance == null)
            instance = new BookingStep3Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_booking_step3,container,false);
    }

    @Override
    public void onTimeSlotLoadSucess(List<TimeSlot> timeSlotList) {

    }

    @Override
    public void onTimeSlotLoadFailed(String message) {

    }

    @Override
    public void onTimeSlotEmpty() {

    }
}

 /*   @BindView(R.id.recycle_time_slot)
    RecyclerView recycle_time_slot;
    @BindView(R.id.calendarView)
    HorizontalCalendarView calendarView;
    SimpleDateFormat simpleDataFormat;

    BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Calendar date = Calendar.getInstance();
                    date.add(Calendar.DATE,0);
                    NamePlace = Prevalent.gethospital();
                    loadAvailableTimeSlotofSelect(Prevalent.currentSelect.getId(),
                    simpleDataFormat.format(date.getTime()));

        }
    };

    private void loadAvailableTimeSlotofSelect(String id, String bookDate) {

        //All/Doctor/Branch/USJVp8B0FaKf6yOnnpsQ

        branchRef = FirebaseFirestore.getInstance()
                .collection("All")
                .document(NamePlace)
                .collection("Branch")
                .document(Prevalent.currentSelect.getId());


        branchRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        CollectionReference date = FirebaseFirestore.getInstance()
                                .collection("All")
                                .document(NamePlace)
                                .collection("Branch")
                                .document(Prevalent.currentSelect.getId())
                                .collection(bookDate);
                        date.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    QuerySnapshot querySnapshot =task.getResult();
                                    if (querySnapshot.isEmpty()) {
                                        iTimeSlotLoadListener.onTimeSlotEmpty();
                                    }
                                    else{
                                        List<TimeSlot> timeSlots = new ArrayList<>();
                                        for(QueryDocumentSnapshot document:task.getResult())
                                            timeSlots.add(document.toObject(TimeSlot.class));
                                        iTimeSlotLoadListener.onTimeSlotLoadSucess(timeSlots);
                                    }

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                iTimeSlotLoadListener.onTimeSlotLoadFailed(e.getMessage());
                            }
                        });

                    }

                }

            }
        });

        //Get information this Customer





    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iTimeSlotLoadListener = this;

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(displayTimeSlot,new IntentFilter(Prevalent.KEY_DISPLAY_TIME_SLOT));
        simpleDataFormat = new SimpleDateFormat("dd_MM_yyyy");

        select_date = Calendar.getInstance();
        select_date.add(Calendar.DATE ,0);
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(displayTimeSlot);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step3,container,false);
        unbinder = ButterKnife.bind(this,itemView);

        init(itemView);
        return itemView;
    }

    private void init(View itemView) {
        recycle_time_slot.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recycle_time_slot.setLayoutManager(gridLayoutManager);
        recycle_time_slot.addItemDecoration(new SPItemDecoration(8));

        //Calendar
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,0);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE,2);  //2 Days left

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(itemView , R.id.calendarView)
                .range(startDate,endDate)
                .datesNumberOnScreen(1)
                .mode(HorizontalCalendar.Mode.DAYS)
                .defaultSelectedDate(startDate)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                if (select_date.getTimeInMillis() != date.getTimeInMillis());{

                    select_date = date;
                    loadAvailableTimeSlotofSelect((Prevalent.currentSelect.getId()),
                            simpleDataFormat.format(date.getTime()));
                }
            }
        });
    }


    @Override
    public void onTimeSlotLoadSucess(List<TimeSlot> timeSlotList) {
        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext(),timeSlotList);
        recycle_time_slot.setAdapter(adapter);
    }

    @Override
    public void onTimeSlotLoadFailed(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSlotEmpty() {
        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext());
        recycle_time_slot.setAdapter(adapter);
    }  */


