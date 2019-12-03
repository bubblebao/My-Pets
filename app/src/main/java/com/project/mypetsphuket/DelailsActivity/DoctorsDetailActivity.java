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
import com.project.mypetsphuket.MapActivity;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class DoctorsDetailActivity extends AppCompatActivity {



    // 8. Defined parameter
    private ItemClickListner itemClickListner;
    private ImageView DoctorImageView;

    private TextView  DoctorName;
    private TextView  DoctorSpecialist;
    private TextView  DoctorWorking;
    private TextView  DoctorServicetime;
    private TextView  DoctorCertication;
    private TextView  DoctorRating;
    private TextView  closeTextBtn;

    private ImageView DoctorMapImag;
    private TextView  DoctorMap;


    private ImageView DoctorPhone;
    private TextView  DoctorCall;

    private ImageView DoctorInformationImag;
    private TextView  DoctorInformation;





    private String Id , ImageView, Name, Phone, Working, Description, Servicetime,
    Longtitude,Latitude , Location  , Specialist, Certificate, Rating;


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

        // 1. Find Object in activity_Doctors_detail
        DoctorImageView = (ImageView) findViewById(R.id.Doctor_Detail_ImageView);
        DoctorName = (TextView) findViewById(R.id.Doctor_Detail_Name);
        DoctorSpecialist = (TextView) findViewById(R.id.Doctor_Detail_Specialist);
        DoctorWorking = (TextView) findViewById(R.id.Doctor_Detail_Working);
        DoctorCertication = (TextView) findViewById(R.id.Doctor_Detail_Certication);
        DoctorRating = (TextView) findViewById(R.id.Doctor_Detail_Rate);
       // DoctorPhoneNumber = (TextView) findViewById(R.id.Doctor_Detail_Phone);

        DoctorMapImag = (ImageView) findViewById(R.id.DoctorMapImageView );
        DoctorMap = (TextView) findViewById(R.id.Doctor_Map);

        DoctorInformationImag = (ImageView) findViewById(R.id.Doctor_InformationImageView );
        DoctorInformation = (TextView) findViewById(R.id.Doctor_Informations);

        DoctorPhone = (ImageView) findViewById(R.id.DoctorPhoneImage);
        DoctorCall = (TextView) findViewById(R.id.Doctor_Call);

        /// 2. Get Data form RecyclerAdepter
        ReceiveDataFormRecycle();


        // 3. Display Data to activity_Doctors_detail
        DisplayDetails();

        DoctorMapImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartMapActivity();

            }
        });

        DoctorMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartMapActivity();

            }
        });


        DoctorInformationImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();
            }
        });

        DoctorInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();
            }
        });

        DoctorCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();

            }
        });


        DoctorPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();

            }
        });

    }

    private void DisplayDetails() {

        Picasso.get().load(ImageView).into(DoctorImageView);
        DoctorName.setText(Name);
        DoctorSpecialist.setText("(Pediatrician)");
        //DoctorSpecialist.setText("Specialist : "+Specialist);
        DoctorWorking.setText(Working);
        DoctorCertication.setText("("+Location +")");
        DoctorRating.setText(Rating);
        // DoctorServicetime.setText("Working time : " +Servicetime);
        // DoctorPhoneNumber.setText(Phone);

    }

    private void ReceiveDataFormRecycle() {
        final Intent intent = getIntent();
        Id = intent.getStringExtra("Id");
        ImageView = intent.getStringExtra("Url");
        Name = intent.getStringExtra("Name");
        Phone = intent.getStringExtra("Phone");
        Description = intent.getStringExtra("Description");
        Specialist = intent.getStringExtra("Specialist");
        Working = intent.getStringExtra("Working");
        Servicetime = intent.getStringExtra("Servicetime");
        Location = intent.getStringExtra("Location");
        Latitude = intent.getStringExtra("Latitude");
        Longtitude = intent.getStringExtra("Longtitude");
        Certificate = intent.getStringExtra("Certificate");
        Rating = intent.getStringExtra("Rating");

    }

    private void StartMapActivity() {

        //Set Data to InformationsActivity
        Intent intentInformation = new Intent( DoctorsDetailActivity.this , MapActivity.class);
        //intentInformation.putExtra("Id",Id);
        intentInformation.putExtra("title","Doctors");
        intentInformation.putExtra("Url",ImageView);
        intentInformation.putExtra("Name",Name);
        intentInformation.putExtra("Working",Working);
        intentInformation.putExtra("Location",Location);
        intentInformation.putExtra("Servicetime",Servicetime);
        intentInformation.putExtra("Rating",Rating);
        intentInformation.putExtra("Latitude",Latitude);
        intentInformation.putExtra("Longtitude",Longtitude);

        startActivity(intentInformation);

    }

    private void StartInformationActivity() {

        //Set Data to InformationsActivity
        Intent intentInformation = new Intent( DoctorsDetailActivity.this , InformationsActivity.class);
        intentInformation.putExtra("title","Doctors");
        intentInformation.putExtra("Url",ImageView);
        intentInformation.putExtra("Name",Name);
        intentInformation.putExtra("Description",Description);
        intentInformation.putExtra("Location",Location);
        intentInformation.putExtra("Servicetime",Servicetime);
        intentInformation.putExtra("Servicetype",Certificate);
        intentInformation.putExtra("Rating",Rating);
        //   intentInformation.putExtra("Certificate",Certificate);

        startActivity(intentInformation);

    }

    private void StartCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String numbers = Phone.toString();

        if (numbers.trim().isEmpty()){

            Toast.makeText(DoctorsDetailActivity.this, "Error",
                    Toast.LENGTH_SHORT).show();

        } else {

            intent.setData(Uri.parse("tel:"+numbers));
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            Toast.makeText(DoctorsDetailActivity.this, "Please PERMISSION GRANTED ",
                    Toast.LENGTH_SHORT).show();
            requestionPerminssion();

        }else {

            startActivity(intent);
        }
    }

    private void requestionPerminssion(){

        ActivityCompat.requestPermissions(
                DoctorsDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
