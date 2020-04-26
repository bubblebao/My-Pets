package com.project.mypetsphuket.BookingFragment;


import android.app.AlertDialog;
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

import com.project.mypetsphuket.Model.BookingDoctor;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.MyDoctorAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep2Fragment extends Fragment {

    Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;

    static BookingStep2Fragment instance;

    @BindView(R.id.recycle_book_doctor)
    RecyclerView recycleView_Doctor_Book;

    public static BookingStep2Fragment getInstance(){
        if (instance == null)
            instance = new BookingStep2Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(doctorDoneReciver,new IntentFilter(Prevalent.KEY_DOCTOR_LOAD_DONE));


    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(doctorDoneReciver);
        super.onDestroy();
    }


    BroadcastReceiver doctorDoneReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<BookingDoctor> bookingDoctorArrayList = intent.getParcelableArrayListExtra(Prevalent.KEY_DOCTOR_LOAD_DONE);
            //Creat adapter
            MyDoctorAdapter adapter = new MyDoctorAdapter(getContext(),bookingDoctorArrayList);
            recycleView_Doctor_Book.setAdapter(adapter);

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View itemView = inflater.inflate(R.layout.fragment_booking_step2,container,false);
        unbinder = ButterKnife.bind(this,itemView);

        init();
        return itemView;


    }

    private void init() {


        recycleView_Doctor_Book.setHasFixedSize(true);
        recycleView_Doctor_Book.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycleView_Doctor_Book.addItemDecoration(new SPItemDecoration(4));
    }

}
