package com.project.mypetsphuket.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.mypetsphuket.R;

public class EmergencyActivity extends AppCompatActivity {
    private TextView closeTextBtn , NextTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        closeTextBtn = (TextView) findViewById(R.id.close_emergency_btn);
        //NextTextButton = (TextView) findViewById(R.id.update_settings_btn);


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });



    }
}
