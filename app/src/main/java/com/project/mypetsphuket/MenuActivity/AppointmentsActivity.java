package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.MyViewPagerAdapter;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class AppointmentsActivity extends AppCompatActivity {
    private TextView   closeTextBtn , NextTextButton;

    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_previous_step)
    Button btn_prevous_step;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        closeTextBtn = (TextView) findViewById(R.id.close_appointment_btn);
        ButterKnife.bind(AppointmentsActivity.this);

        setupStepView();
        setColorButtom();

        //view
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    btn_prevous_step.setEnabled(false);
                else
                    btn_next_step.setEnabled(true);
                setColorButtom();
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    private void setColorButtom() {

        if (btn_next_step.isEnabled()){

            btn_next_step.setBackgroundResource(R.color.colorButton);
        }
        else {

            btn_next_step.setBackgroundResource(R.color.dark_grey);
        }
        if (btn_prevous_step.isEnabled()){

            btn_prevous_step.setBackgroundResource(R.color.colorButton);
        }
        else {

            btn_prevous_step.setBackgroundResource(R.color.dark_grey);
        }

    }

    private void setupStepView() {
        List<String> stepList = new ArrayList<>();
        stepList.add("Choose");
        stepList.add("Select");
        stepList.add("Time");
        stepList.add("Confirm");
        stepView.setSteps(stepList);
    }


}
