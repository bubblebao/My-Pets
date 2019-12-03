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
import com.project.mypetsphuket.MapActivity;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class PetshopsDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView PetshopImageView;
    private TextView  PetshopName;
    private TextView  PetshopDescription;
    private TextView  PetshopLocation;
    private TextView  PetshopServicetype;
    private TextView  PetshopServicetime;
    private TextView  PetshopRating;
    private TextView  closeTextBtn;

    private ImageView PetshopMapImag;
    private TextView  PetshopMap;

    private ImageView PetshopInformationImag;
    private  TextView PetshopInformation;

    private TextView PetshopCall;
    private ImageView PetshopPhone;

    private String ImageView, Name, Phone, Description, Servicetime,
            Location, Longtitude, Latitude ,Servicetype, Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petshops_detail);

        closeTextBtn = (TextView) findViewById(R.id.close_Petshop_detail_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // 1.Find Object in activity_Petshops_detail
        PetshopImageView = (ImageView) findViewById(R.id.Petshop_Detail_ImageView);
        PetshopName = (TextView) findViewById(R.id.Petshop_Detail_Name);
        PetshopServicetype = (TextView) findViewById(R.id.Petshop_Detail_Servicetype);
        PetshopLocation = (TextView) findViewById(R.id.Petshop_Detail_Location);
        PetshopRating = (TextView) findViewById(R.id.Petshop_Detail_Rate);
        //  PetshopServicetime = (TextView) findViewById(R.id.Petshop_Detail_Servicetime);

        PetshopMapImag = (ImageView) findViewById(R.id.PetshopMapImageView );
        PetshopMap = (TextView) findViewById(R.id.Petshop_Map);

        PetshopInformationImag = (ImageView) findViewById(R.id. Petshop_InformationImageView);
        PetshopInformation = (TextView) findViewById(R.id.Petshop_Informations);

        PetshopPhone = (ImageView) findViewById(R.id. PetshopPhoneImage);
        PetshopCall = (TextView) findViewById(R.id.Petshop_Call);

        /// 2. Get Data form RecyclerAdepter
        ReceiveDataFormRecycle();

        // 3. Display Data to activity_Petshops_detail
        DisplayDetails();


        PetshopMapImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartMapActivity();

            }
        });

        PetshopMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartMapActivity();

            }
        });

        PetshopCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();

            }
        });

        PetshopPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartCall();

            }
        });

        PetshopInformationImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartInformationActivity();
            }
        });

        PetshopInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartInformationActivity();

            }
        });

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

    private void DisplayDetails() {

        Picasso.get().load(ImageView).into(PetshopImageView);
        PetshopName.setText(Name);
        PetshopServicetype.setText(Servicetype);
        PetshopLocation.setText("("+Location +")");
        PetshopRating.setText(Rating);
        //PetshopServicetime.setText("Open time : "+Servicetime);
    }


    private void StartMapActivity() {

        //Set Data to InformationsActivity
        Intent intentInformation = new Intent( PetshopsDetailActivity.this , MapActivity.class);
        //intentInformation.putExtra("Id",Id);
        intentInformation.putExtra("title","Petshops");
        intentInformation.putExtra("Url",ImageView);
        intentInformation.putExtra("Name",Name);
        intentInformation.putExtra("Working",Description);
        intentInformation.putExtra("Location",Location);
        intentInformation.putExtra("Latitude",Latitude);
        intentInformation.putExtra("Longtitude",Longtitude);
        intentInformation.putExtra("Servicetime",Servicetime);
        intentInformation.putExtra("Rating",Rating);

        startActivity(intentInformation);
    }

    private void StartInformationActivity() {

        //Set Data to InformationsActivity
        Intent intentInformation = new Intent( PetshopsDetailActivity.this , InformationsActivity.class);
        intentInformation.putExtra("title","PetShops");
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

            Toast.makeText(PetshopsDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

        } else {

            intent.setData(Uri.parse("tel:"+numbers));
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            Toast.makeText(PetshopsDetailActivity.this, "Please PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
            requestionPerminssion();

        }else {

            startActivity(intent);
        }
    }




    private void requestionPerminssion() {

        ActivityCompat.requestPermissions(PetshopsDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}
