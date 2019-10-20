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
    private ImageView EmergencysImageCall;
    private TextView  EmergencyName;
    private TextView  EmergencyDescription;
    private TextView  EmergencyLocation;
    private TextView  EmergencysCall;
    private TextView  EmergencysPhoneNumber;
    private TextView  EmergencyServicetype;
    private TextView  Emergencyservicetime;
    private TextView  EmergencyRating;
    private TextView  closeTextBtn;

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

        //Find Object in activity_Emergencys_detail
        EmergencyImageView = (ImageView) findViewById(R.id.Emergency_Detail_ImageView);
        EmergencyName = (TextView) findViewById(R.id.Emergency_Detail_Name);
        EmergencysPhoneNumber = (TextView) findViewById(R.id.Emergency_Detail_Phone);
        EmergencyServicetype = (TextView) findViewById(R.id.Emergency_Detail_Servicetype);
        Emergencyservicetime = (TextView) findViewById(R.id.Emergency_Detail_Servicetime);
        EmergencyLocation = (TextView) findViewById(R.id.Emergency_Detail_Location);
        EmergencyRating = (TextView) findViewById(R.id.Emergency_Detail_Rate);

        EmergencysImageCall = (ImageView) findViewById(R.id.EmergencyPhoneImage);
        EmergencysCall = (TextView) findViewById(R.id.Emergency_Call);


        ///Get Data form RecyclerAdepter
        Intent intent = getIntent();
        String ImageView = intent.getStringExtra("Url");
        String Name = intent.getStringExtra("Name");
        String Description = intent.getStringExtra("Description");
        String Servicetype = intent.getStringExtra("Servicetype");
        String Servicetime = intent.getStringExtra("Servicetime");
        final String Phone = intent.getStringExtra("Phone");
        String Location = intent.getStringExtra("Location");
        String Rating = intent.getStringExtra("Rating");

        //Display Data to activity_Emergencys_detail
        Picasso.get().load(ImageView).into(EmergencyImageView);
        EmergencyName.setText(Name);
        EmergencyServicetype.setText(Servicetype);
        Emergencyservicetime.setText(Servicetime);
        EmergencyLocation.setText("("+Location +")");
        EmergencysPhoneNumber.setText(Phone);
        EmergencyRating.setText(Rating);

        EmergencysCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

        EmergencysImageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

    }

    private void requestionPerminssion() {
        ActivityCompat.requestPermissions(EmergencysDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
