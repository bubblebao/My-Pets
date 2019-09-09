package com.project.mypetsphuket.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.mypetsphuket.R;

public class DoctorsActivity extends AppCompatActivity {
    private TextView   closeTextBtn , NextTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        closeTextBtn = (TextView) findViewById(R.id.close_doctors_btn);
      //  NextTextButton = (TextView) findViewById(R.id.update_settings_btn);


        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });


    }
}
