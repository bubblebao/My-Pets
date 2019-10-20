package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class DoctorsDetailActivity extends AppCompatActivity {



    // 8. Defined parameter
    private ItemClickListner itemClickListner;
    private ImageView DoctorImageView;
    private ImageView DoctorPhone;
    private TextView  DoctorPhoneNumber;
    private TextView  DoctorName;
    private TextView  DoctorSpecialist;
    private TextView  DoctorWorking;
    private TextView  DoctorServicetime;
    private TextView  DoctorLocation;
    private TextView  DoctorRating;
    private TextView  closeTextBtn;

    private TextView DoctorCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_detail);

        closeTextBtn = (TextView) findViewById(R.id.close_Doctor_detail_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // 9. Find Object in activity_Doctors_detail
        DoctorImageView = (ImageView) findViewById(R.id.Doctor_Detail_ImageView);
        DoctorName = (TextView) findViewById(R.id.Doctor_Detail_Name);

        DoctorSpecialist = (TextView) findViewById(R.id.Doctor_Detail_Specialist);
        DoctorWorking = (TextView) findViewById(R.id.Doctor_Detail_Working);
        DoctorServicetime = (TextView) findViewById(R.id.Doctor_Detail_Servicetime);
        DoctorLocation = (TextView) findViewById(R.id.Doctor_Detail_Location);
        DoctorRating = (TextView) findViewById(R.id.Doctor_Detail_Rate);
        DoctorPhoneNumber = (TextView) findViewById(R.id.Doctor_Detail_Phone);

        DoctorPhone = (ImageView) findViewById(R.id.DoctorPhoneImage);
        DoctorCall = (TextView) findViewById(R.id.Doctor_Call);

        ///10. Get Data form RecyclerAdepter
        final Intent intent = getIntent();
        String ImageView = intent.getStringExtra("Url");
        String Name = intent.getStringExtra("Name");
        final  String Phone = intent.getStringExtra("Phone");
        String Specialist = intent.getStringExtra("Specialist");
        String Working = intent.getStringExtra("Working");
        String Servicetime = intent.getStringExtra("Servicetime");
        String Location = intent.getStringExtra("Location");
        String Rating = intent.getStringExtra("Rating");

        //10 Display Data to activity_Doctors_detail
        Picasso.get().load(ImageView).into(DoctorImageView);
        DoctorName.setText(Name);
        DoctorSpecialist.setText("Specialist : "+Specialist);
        DoctorWorking.setText(Working);
        DoctorServicetime.setText("Working time : " +Servicetime);
        DoctorLocation.setText("("+Location +")");
        DoctorPhoneNumber.setText(Phone);
        DoctorRating.setText(Rating);



        DoctorCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                String numbers = Phone.toString();

                if (numbers.trim().isEmpty()){

                    Toast.makeText(DoctorsDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

                } else {

                    intent.setData(Uri.parse("tel:"+numbers));
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(DoctorsDetailActivity.this, "Please PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
                    requestionPerminssion();

                }else {

                    startActivity(intent);
                }

            }
        });


        DoctorPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    String numbers = Phone.toString();

                    if (numbers.trim().isEmpty()){

                        Toast.makeText(DoctorsDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    } else {

                        intent.setData(Uri.parse("tel:"+numbers));
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(DoctorsDetailActivity.this, "Please PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
                        requestionPerminssion();

                    }else {

                        startActivity(intent);
                    }


            }
        });

    }
    private void requestionPerminssion(){

        ActivityCompat.requestPermissions(DoctorsDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
