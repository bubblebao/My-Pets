package com.project.mypetsphuket.RecycleAdepter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.project.mypetsphuket.BookingFragment.BookingStep1Fragment;
import com.project.mypetsphuket.BookingFragment.BookingStep2Fragment;
import com.project.mypetsphuket.BookingFragment.BookingStep3Fragment;
import com.project.mypetsphuket.BookingFragment.BookingStep4Fragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return BookingStep1Fragment.getInstance();
            case 1:
                return BookingStep2Fragment.getInstance();
            case 2:
                return BookingStep3Fragment.getInstance();
            case 3:
                return BookingStep4Fragment.getInstance();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
