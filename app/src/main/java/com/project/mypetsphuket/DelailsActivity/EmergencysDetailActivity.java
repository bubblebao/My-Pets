package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class EmergencysDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView EmergencyImageView;
    private TextView  EmergencyName;
    private TextView  EmergencyDescription;
    private TextView  EmergencyLocation;
    private TextView  Emergencyservicetype;
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
        EmergencyDescription = (TextView) findViewById(R.id.Emergency_Detail_Description);
        EmergencyLocation = (TextView) findViewById(R.id.Emergency_Detail_Location);
        EmergencyRating = (TextView) findViewById(R.id.Emergency_Detail_Rate);

        ///Get Data form RecyclerAdepter
        Intent intent = getIntent();
        String ImageView = intent.getStringExtra("Url");
        String Name = intent.getStringExtra("Name");
        String Description = intent.getStringExtra("Description");
   //     String servicetype = intent.getStringExtra("servicetype");
   //     String servicetime = intent.getStringExtra("servicetime");
        String Location = intent.getStringExtra("Location");
        String Rating = intent.getStringExtra("Rating");

        //Display Data to activity_Emergencys_detail
        Picasso.get().load(ImageView).into(EmergencyImageView);
        EmergencyName.setText(Name);
        EmergencyDescription.setText(Description);
        EmergencyLocation.setText(Location);
        EmergencyRating.setText(Rating);

    }
}
