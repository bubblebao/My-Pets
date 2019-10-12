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

public class PetshopsDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView PetshopImageView;
    private ImageView PetshopPhone;
    private TextView  PetshopName;
    private TextView  PetshopDescription;
    private TextView  PetshopLocation;
    private TextView  PetshopServicetype;
    private TextView  PetshopServicetime;
    private TextView  PetshopRating;
    private TextView  closeTextBtn;

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

        //Find Object in activity_Petshops_detail
        PetshopImageView = (ImageView) findViewById(R.id.Petshop_Detail_ImageView);
        PetshopPhone = (ImageView) findViewById(R.id. Petshop_PhoneImage);
        PetshopName = (TextView) findViewById(R.id.Petshop_Detail_Name);
        PetshopServicetype = (TextView) findViewById(R.id.Petshop_Detail_Servicetype);
        PetshopServicetime = (TextView) findViewById(R.id.Petshop_Detail_Servicetime);
        PetshopLocation = (TextView) findViewById(R.id.Petshop_Detail_Location);
        PetshopRating = (TextView) findViewById(R.id.Petshop_Detail_Rate);

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

        //Display Data to activity_Petshops_detail
        Picasso.get().load(ImageView).into(PetshopImageView);
        PetshopName.setText(Name);
        PetshopServicetype.setText(Servicetype);
        PetshopServicetime.setText("Open time : "+Servicetime);
        //  PetshopDescription.setText(Description);
        PetshopLocation.setText("("+Location +")");
        PetshopRating.setText(Rating);

        PetshopPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

    }

    private void requestionPerminssion() {

        ActivityCompat.requestPermissions(PetshopsDetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}
