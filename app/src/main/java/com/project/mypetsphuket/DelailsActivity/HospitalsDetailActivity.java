package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.MapActivity;
import com.project.mypetsphuket.Model.Informations;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class HospitalsDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView HospitalImageView;

    private TextView  HospitalName;
    private TextView  HospitalDescription;
    private TextView  HospitalServicetype;
    private TextView  HospitalServicetime;
    private TextView  HospitalLocation;
    private TextView  HospitalRating;

    private ImageView HospitalMapImag;
    private TextView  HospitalMap;

    private ImageView HospitalInformationImag;
    private TextView  HospitalInformation;

    private ImageView HospitalPhone;
    private TextView  HospitalCall;





    private String ImageView, Name, Phone, Description, Servicetime, Location, Longtitude, Latitude , Servicetype, Rating;



    private TextView  closeTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_detail);

        closeTextBtn = (TextView) findViewById(R.id.close_Hospital_detail_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        //1.Find Object in activity_hospitals_detail
        HospitalImageView = (ImageView) findViewById(R.id.Hospital_Detail_ImageView);
        HospitalName = (TextView) findViewById(R.id.Hospital_Detail_Name);
        HospitalServicetype = (TextView) findViewById(R.id.Hospital_Detail_Servicetype);
        HospitalLocation = (TextView) findViewById(R.id.Hospital_Detail_Location);
        HospitalRating = (TextView) findViewById(R.id.Hospital_Detail_Rate);
        //  HospitalServicetime = (TextView) findViewById(R.id.Hospital_Detail_Servicetime);

        HospitalMapImag = (ImageView) findViewById(R.id.HospitalMapImageView );
        HospitalMap = (TextView) findViewById(R.id.Hospital_Map);

        HospitalInformationImag = (ImageView) findViewById(R.id. Hospital_InformationImageView);
        HospitalInformation = (TextView) findViewById(R.id.Hospital_Informations);

        HospitalPhone = (ImageView) findViewById(R.id. HospitalPhoneImage);
        HospitalCall = (TextView) findViewById(R.id.Hospital_Call);

        ///2. Get Data form RecyclerAdepter
        ReceiveDataFormRecycle();

        //3. Display Data to activity_hospitals_detail
        DisplayDetails();

        HospitalMapImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartMapActivity();

            }
        });

        HospitalMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartMapActivity();

            }
        });


        HospitalInformationImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();

            }
        });

        HospitalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();
            }
        });



        HospitalCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();
            }
        });

        HospitalPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();

            }
        });

    }


    private void DisplayDetails() {

        Picasso.get().load(ImageView).into(HospitalImageView);
        HospitalName.setText(Name);
        HospitalServicetype.setText(Servicetype);
        HospitalLocation.setText("("+Location +")");
        HospitalRating.setText(Rating);

        HospitalRating.setVisibility(View.INVISIBLE);
        // HospitalServicetime.setText("Servicetime : " +Servicetime);
    }

    private void ReceiveDataFormRecycle() {

        Intent intent = getIntent();
        ImageView = intent.getStringExtra("Url");
        Name = intent.getStringExtra("Name");
        Phone = intent.getStringExtra("Phone");
        Description = intent.getStringExtra("Description");
        Servicetype = intent.getStringExtra("Servicetype");
        Servicetime = intent.getStringExtra("Servicetime");
        Location = intent.getStringExtra("Location");
        Latitude = intent.getStringExtra("Latitude");
        Longtitude = intent.getStringExtra("Longtitude");
        Rating = intent.getStringExtra("Rating");

    }

    private void StartMapActivity() {

        //Set Data to InformationsActivity
        Intent intentInformation = new Intent( HospitalsDetailActivity.this , MapActivity.class);
        //intentInformation.putExtra("Id",Id);
        intentInformation.putExtra("title","Hospitals");
        intentInformation.putExtra("Url",ImageView);
        intentInformation.putExtra("Name",Name);
        intentInformation.putExtra("Description",Description);
        intentInformation.putExtra("Location",Location);
        intentInformation.putExtra("Latitude",Latitude);
        intentInformation.putExtra("Longtitude",Longtitude);
        intentInformation.putExtra("Servicetime",Servicetime);
        intentInformation.putExtra("Rating",Rating);



        startActivity(intentInformation);

    }

    private void StartInformationActivity() {

        //Set Data to InformationsActivity
        Intent intentInformation = new Intent( HospitalsDetailActivity.this , InformationsActivity.class);
        intentInformation.putExtra("title","Hospital");
        intentInformation.putExtra("Url",ImageView);
        intentInformation.putExtra("Name",Name);
        intentInformation.putExtra("Description",Description);
        intentInformation.putExtra("Location",Location);
        intentInformation.putExtra("Servicetime",Servicetime);
        intentInformation.putExtra("Servicetype",Servicetype);
        intentInformation.putExtra("Rating",Rating);
        startActivity(intentInformation);

    }

    private void StartCall() {

        Intent intent = new Intent(Intent.ACTION_CALL);
        String numbers = Phone.toString();

        if (numbers.trim().isEmpty()){

            Toast.makeText(HospitalsDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

        } else {

            intent.setData(Uri.parse("tel:"+numbers));
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            Toast.makeText(HospitalsDetailActivity.this, "Please PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
            requestionPerminssion();

        }else {

            startActivity(intent);
        }
    }



    private void requestionPerminssion() {

        ActivityCompat.requestPermissions(HospitalsDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}
