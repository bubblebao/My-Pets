package com.project.mypetsphuket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mypetsphuket.AddNewActivity.AddNewDoctorsActivity;
import com.project.mypetsphuket.AddNewActivity.AddNewEmergencyActivity;
import com.project.mypetsphuket.AddNewActivity.AddNewHospitalsActivity;
import com.project.mypetsphuket.AddNewActivity.AddNewPetshopActivity;
import com.project.mypetsphuket.AddNewActivity.AdminAddNewProductActivity;

public class AdminCategoryActivity extends AppCompatActivity {

    private TextView closeTextBtn , NextTextButton;
    private Button LogoutBtn;

    private ImageView doctor_admin_add , petshop_admin_add , hospital_admin_add , emergency_admin_add  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        Toast.makeText(AdminCategoryActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();


        doctor_admin_add = (ImageView) findViewById(R.id.doctor_admin_category);
        petshop_admin_add = (ImageView) findViewById(R.id.petshop_admin_category);
        hospital_admin_add = (ImageView) findViewById(R.id.hospital_admin_category);
        emergency_admin_add = (ImageView) findViewById(R.id.emergency_admin_category);
        closeTextBtn = (TextView) findViewById(R.id.close_admin_btn);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });


        doctor_admin_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this, AddNewDoctorsActivity.class);
                intent.putExtra("category", "doctor");
                startActivity(intent);
            }
        });

       hospital_admin_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(AdminCategoryActivity.this, AddNewHospitalsActivity.class);
               intent.putExtra("category", "hospital");
               startActivity(intent);
           }
       });

       petshop_admin_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AdminCategoryActivity.this, AddNewPetshopActivity.class);
               intent.putExtra("category", "petshop");
               startActivity(intent);

           }
       });



       emergency_admin_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(AdminCategoryActivity.this, AddNewEmergencyActivity.class);
               intent.putExtra("category", "emergency");
               startActivity(intent);

           }
       });

    }
}
