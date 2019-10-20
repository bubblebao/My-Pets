package com.project.mypetsphuket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    private Button CallUs;

    private TextView closeTextBtn , NextTextButton;
    private String AdminPhoneNumbers = ("+66822828303") ;
    private String adminsPhoneNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        closeTextBtn = (TextView) findViewById(R.id.close_aboutus_btn);

        CallUs = (Button) findViewById(R.id.Call_Us);



        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        CallUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                String numbers = AdminPhoneNumbers ;

                if (numbers.trim().isEmpty()){

                    Toast.makeText(AboutUsActivity.this, "Error", Toast.LENGTH_SHORT).show();

                } else {

                    intent.setData(Uri.parse("tel:"+numbers));
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(AboutUsActivity.this, "Please PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
                    requestionPerminssion();

                }else {

                    startActivity(intent);
                }
            }
        });
    }

    private void requestionPerminssion() {

        ActivityCompat.requestPermissions(AboutUsActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
