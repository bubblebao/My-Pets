package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.ActionBar;
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

public class HospitalsDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView HospitalImageView;
    private ImageView HospitalPhone;
    private TextView  HospitalName;
    private TextView HospitalDescription;
    private TextView HospitalServicetype;
    private TextView HospitalServicetime;
    private TextView HospitalLocation;
    private TextView HospitalRating;
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

        //Find Object in activity_hospitals_detail
        HospitalImageView = (ImageView) findViewById(R.id.Hospital_Detail_ImageView);
        HospitalPhone = (ImageView) findViewById(R.id. HospitalPhoneImage);
        HospitalName = (TextView) findViewById(R.id.Hospital_Detail_Name);
        HospitalServicetype = (TextView) findViewById(R.id.Hospital_Detail_Servicetype);
        HospitalServicetime = (TextView) findViewById(R.id.Hospital_Detail_Servicetime);
        HospitalLocation = (TextView) findViewById(R.id.Hospital_Detail_Location);
        HospitalRating = (TextView) findViewById(R.id.Hospital_Detail_Rate);

        ///Get Data form RecyclerAdepter
        Intent intent = getIntent();
        String ImageView = intent.getStringExtra("Url");
        String Name = intent.getStringExtra("Name");
        final String Phone = intent.getStringExtra("Phone");
        String Description = intent.getStringExtra("Description");
        String Servicetype = intent.getStringExtra("Servicetype");
        String Servicetime = intent.getStringExtra("Servicetime");
        String Location = intent.getStringExtra("Location");
        String Rating = intent.getStringExtra("Rating");

        //Display Data to activity_hospitals_detail
        Picasso.get().load(ImageView).into(HospitalImageView);
        HospitalName.setText(Name);
        HospitalServicetype.setText(Servicetype);
        HospitalServicetime.setText("Servicetime : " +Servicetime);
        HospitalLocation.setText("("+Location +")");
        HospitalRating.setText(Rating);

        HospitalPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });





    }

    private void requestionPerminssion() {

        ActivityCompat.requestPermissions(HospitalsDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}
