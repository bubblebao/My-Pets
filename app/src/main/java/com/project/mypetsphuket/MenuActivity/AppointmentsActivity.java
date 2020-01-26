package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mypetsphuket.Prevalent.Common;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.MyViewPagerAdapter;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AppointmentsActivity extends AppCompatActivity {
    private TextView   closeTextBtn , NextTextButton;

    LocalBroadcastManager localBroadcastManager;

    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_previous_step)
    Button btn_prevous_step;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;

    @OnClick(R.id.btn_next_step)
    void nextClick(){

    /*    if (Prevalent.step < 3 || Prevalent.step > 0){

            Prevalent.step++; //+
        }
          if (Prevalent.step == 1){

              if(Prevalent.currentSelect != null)
                  loadObject(Prevalent.currentSelect.getId());


          }  */


    Toast.makeText(this,""+Prevalent.currentSelect.getName(),Toast.LENGTH_SHORT).show();

    }


    //Broadcast Reciver
    private BroadcastReceiver buttonNextReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Prevalent.currentSelect = intent.getParcelableExtra(Prevalent.KEY_ITEM_STORE);
            btn_next_step.setEnabled(true);
            setColorButtom();
        }
    };

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(buttonNextReciver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        closeTextBtn = (TextView) findViewById(R.id.close_appointment_btn);
        ButterKnife.bind(AppointmentsActivity.this);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReciver ,new IntentFilter(Prevalent.KEY_ENABLE_NEXT));

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
        stepList.add("Time");
        stepList.add("Confirm");
        stepView.setSteps(stepList);
    }


}
