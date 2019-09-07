package com.project.mypetsphuket.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.project.mypetsphuket.R;

public class HospitalsActivity extends AppCompatActivity {

    private TextView closeTextBtn , NextTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        closeTextBtn = (TextView) findViewById(R.id.close_hospitals_btn);
        NextTextButton = (TextView) findViewById(R.id.petshop_next_btn);


        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
      //  FirebaseRecyclerAdapter;
        super.onStart();
    }
}
