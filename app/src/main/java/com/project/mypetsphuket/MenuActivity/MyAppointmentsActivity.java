package com.project.mypetsphuket.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.mypetsphuket.R;

public class MyAppointmentsActivity extends AppCompatActivity {
    private TextView   closeTextBtn , NextTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        closeTextBtn = (TextView) findViewById(R.id.close_my_appointment_btn);
        NextTextButton = (TextView) findViewById(R.id.appointment_next_btn);


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
