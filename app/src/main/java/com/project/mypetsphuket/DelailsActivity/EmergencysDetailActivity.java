package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class EmergencysDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView EmergencyImageView;
    private TextView  EmergencyName;
    private TextView  EmergencyDescription;
    private TextView  EmergencyLocation;
    private TextView  EmergencyServicetype;
    private TextView  EmergencyRating;
    private TextView  closeTextBtn;

    private ImageView EmergencysCallImage;
    private TextView  EmergencysCall;

    private ImageView EmergencysInformationImag;
    private TextView  EmergencysInformations;


    private String ImageView, Name, Phone, Description, Servicetime, Location, Servicetype, Rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencys_detail);

        closeTextBtn = (TextView) findViewById(R.id.close_Emergency_detail_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        //1. Find Object in activity_Emergencys_detail
        EmergencyImageView = (ImageView) findViewById(R.id.Emergency_Detail_ImageView);
        EmergencyName = (TextView) findViewById(R.id.Emergency_Detail_Name);
        EmergencyServicetype = (TextView) findViewById(R.id.Emergency_Detail_Servicetype);
        EmergencyLocation = (TextView) findViewById(R.id.Emergency_Detail_Location);
        EmergencyRating = (TextView) findViewById(R.id.Emergency_Detail_Rate);
        // EmergencysPhoneNumber = (TextView) findViewById(R.id.Emergency_Detail_Phone);
        // Emergencyservicetime = (TextView) findViewById(R.id.Emergency_Detail_Servicetime);

        EmergencysInformationImag = (ImageView) findViewById(R.id.Emergency_InformationImageView);
        EmergencysInformations = (TextView) findViewById(R.id.Emergency_Informations);

        EmergencysCallImage = (ImageView) findViewById(R.id.EmergencyPhoneImage);
        EmergencysCall = (TextView) findViewById(R.id.Emergency_Call);

        ///2. Get Data form RecyclerAdepter
        ReceiveDataFormRecycle();

        //3. Display Data to activity_Emergencys_detail
        DisplayDetails();

        EmergencysInformationImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();
            }
        });

        EmergencysInformations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();
            }
        });

        EmergencysCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();
            }
        });

        EmergencysCallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();
            }
        });

    }

    private void DisplayDetails() {

        Picasso.get().load(ImageView).into(EmergencyImageView);
        EmergencyName.setText(Name);
        EmergencyServicetype.setText(Servicetype);
        EmergencyLocation.setText("("+Location +")");
        EmergencyRating.setText(Rating);

    }

    private void StartInformationActivity() {

        //Set Data to Informations Activity
        Intent intentInformation = new Intent( EmergencysDetailActivity.this , InformationsActivity.class);
        intentInformation.putExtra("title","Emergency");
        intentInformation.putExtra("Url",ImageView);
        intentInformation.putExtra("Name",Name);
        intentInformation.putExtra("Description",Description);
        intentInformation.putExtra("Location",Location);
        intentInformation.putExtra("Servicetime",Servicetime);
        intentInformation.putExtra("Servicetype",Servicetype);
        intentInformation.putExtra("Rating",Rating);
        startActivity(intentInformation);
    }

    private void ReceiveDataFormRecycle() {


        Intent intent = getIntent();
        ImageView = intent.getStringExtra("Url");
        Name = intent.getStringExtra("Name");
        Description = intent.getStringExtra("Description");
        Location = intent.getStringExtra("Location");
        Servicetype = intent.getStringExtra("Servicetype");
        Servicetime = intent.getStringExtra("Servicetime");
        Phone = intent.getStringExtra("Phone");
        Rating = intent.getStringExtra("Rating");

    }

    private void StartCall() {

        Intent intent = new Intent(Intent.ACTION_CALL);
        String numbers = Phone.toString();

        if (numbers.trim().isEmpty()){

            Toast.makeText(EmergencysDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

        } else {

            intent.setData(Uri.parse("tel:"+numbers));
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            Toast.makeText(EmergencysDetailActivity.this, "Please PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
            requestionPerminssion();

        }else {

            startActivity(intent);
        }
    }

    private void requestionPerminssion() {
        ActivityCompat.requestPermissions(EmergencysDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }


}
