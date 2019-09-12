package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class HospitalsDetailActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView HospitalImageView;
    private TextView HospitalName;
    private TextView HospitalDescription;
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
        HospitalName = (TextView) findViewById(R.id.Hospital_Detail_Name);
        HospitalDescription = (TextView) findViewById(R.id.Hospital_Detail_Description);
        HospitalLocation = (TextView) findViewById(R.id.Hospital_Detail_Location);
        HospitalRating = (TextView) findViewById(R.id.Hospital_Detail_Rate);

        ///Get Data form RecyclerAdepter
        Intent intent = getIntent();
        String ImageView = intent.getStringExtra("Url");
        String Name = intent.getStringExtra("Name");
        String Description = intent.getStringExtra("Description");
        String Location = intent.getStringExtra("Location");
        String Rating = intent.getStringExtra("Rating");

        //Display Data to activity_hospitals_detail
        Picasso.get().load(ImageView).into(HospitalImageView);
        HospitalName.setText(Name);
        HospitalDescription.setText(Description);
        HospitalLocation.setText(Location);
        HospitalRating.setText(Rating);



    }
}
