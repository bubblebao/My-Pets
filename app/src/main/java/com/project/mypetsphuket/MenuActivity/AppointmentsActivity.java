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
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.mypetsphuket.Model.DoctorAndHospital;
import com.project.mypetsphuket.Prevalent.NonSwipeViewPager;
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


    //Variable
    CollectionReference allServiceRef;
    CollectionReference branchRef;

    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    NonSwipeViewPager viewPager;
    @BindView(R.id.btn_previous_step)
    Button btn_prevous_step;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;

    @OnClick(R.id.btn_previous_step)
    void  previousStep(){

        if (Prevalent.step == 2 || Prevalent.step > 0 ){
            Prevalent.step--;
            viewPager.setCurrentItem(Prevalent.step);

        }
    }
    @OnClick(R.id.btn_next_step)
    void nextClick(){

        if (Prevalent.step < 2 || Prevalent.step == 0) {

            Prevalent.step++; //+

            if (Prevalent.step == 1) {

                if (Prevalent.currentSelect != null)
                    loadObject(Prevalent.currentSelect.getId());


            }else if (Prevalent.step == 2) {// time slot

                if (Prevalent.currentSelect != null)
                    loadTimeSlotOfBooking(Prevalent.currentSelect.getId());
            }

            viewPager.setCurrentItem(Prevalent.step);

        }
        //  Toast.makeText(this,""+Prevalent.currentSelect.getName(),Toast.LENGTH_SHORT).show();

    }

    private void loadTimeSlotOfBooking(String id) {
        //Send local Braodcast step2
        Intent intent = new Intent(Prevalent.KEY_DISPLAY_TIME_SLOT);
        localBroadcastManager.sendBroadcast(intent);

    }

    private void loadObject(String id) {

        if (!TextUtils.isEmpty((CharSequence) Prevalent.currentSelect)) {
            {
                branchRef = FirebaseFirestore.getInstance()
                        .collection("All")
                        .document(id)
                        .collection("Branch");
            }
            branchRef.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            ArrayList<DoctorAndHospital> doctorAndHospitals = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                DoctorAndHospital doctorAndHospital = documentSnapshot.toObject(DoctorAndHospital.class);
                                doctorAndHospital.setId(documentSnapshot.getId());
                       /*         doctorAndHospital.setName(documentSnapshot.getData().get("name").toString());
                                doctorAndHospital.setPhone(documentSnapshot.getData().get("phone").toString());
                                doctorAndHospital.setServicetime(documentSnapshot.getData().get("servicetime").toString());
                                doctorAndHospital.setAddress(documentSnapshot.getData().get("address").toString());
                                doctorAndHospital.setRating(documentSnapshot.getLong("rating"));
                                doctorAndHospitals.add(doctorAndHospital);  */
                            }

                            //Sent Brostcast
                            Intent intent = new Intent(Prevalent.KEY_SNAPSHOT_LOAD_DONE);
                            intent.putParcelableArrayListExtra(Prevalent.KEY_SNAPSHOT_LOAD_DONE,doctorAndHospitals);
                            localBroadcastManager.sendBroadcast(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {


                }
            });

        }
    }


    //Broadcast Reciver
    private BroadcastReceiver buttonNextReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            int step = intent.getIntExtra(Prevalent.KEY_STEP ,0 );
            if (step == 1)
                Prevalent.currentSelect = intent.getParcelableExtra(Prevalent.KEY_ITEM_STORE);
            else if (step == 2)
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
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    btn_prevous_step.setEnabled(false);
                    btn_next_step.setEnabled(false);
                }
                else
                    btn_prevous_step.setEnabled(true);
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
