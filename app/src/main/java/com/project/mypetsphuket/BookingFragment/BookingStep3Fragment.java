package com.project.mypetsphuket.BookingFragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mypetsphuket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep3Fragment extends Fragment {

    static BookingStep3Fragment instance;

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

        return inflater.inflate(R.layout.fragment_booking_step3,container,false);    }
}
