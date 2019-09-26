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

public class DoctorsDetailActivity extends AppCompatActivity {

    // 8. Defined parameter
    private ItemClickListner itemClickListner;
    private ImageView DoctorImageView;
    private TextView  DoctorName;
    private TextView  DoctorSpecialist;
    private TextView  DoctorWorking;
    private TextView  DoctorServicetime;
    private TextView  DoctorLocation;
    private TextView  DoctorRating;
    private TextView  closeTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_detail);

        closeTextBtn = (TextView) findViewById(R.id.close_Doctor_detail_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // 9. Find Object in activity_Doctors_detail
        DoctorImageView = (ImageView) findViewById(R.id.Doctor_Detail_ImageView);
        DoctorName = (TextView) findViewById(R.id.Doctor_Detail_Name);
        DoctorSpecialist = (TextView) findViewById(R.id.Doctor_Detail_Specialist);
        DoctorWorking = (TextView) findViewById(R.id.Doctor_Detail_Working);
        DoctorServicetime = (TextView) findViewById(R.id.Doctor_Detail_Servicetime);
        DoctorLocation = (TextView) findViewById(R.id.Doctor_Detail_Location);
        DoctorRating = (TextView) findViewById(R.id.Doctor_Detail_Rate);

        ///10. Get Data form RecyclerAdepter
        Intent intent = getIntent();
        String ImageView = intent.getStringExtra("Url");
        String Name = intent.getStringExtra("Name");
        String Specialist = intent.getStringExtra("Specialist");
        String Working = intent.getStringExtra("Working");
        String Servicetime = intent.getStringExtra("Servicetime");
        String Location = intent.getStringExtra("Location");
        String Rating = intent.getStringExtra("Rating");

        //10 Display Data to activity_Doctors_detail
        Picasso.get().load(ImageView).into(DoctorImageView);
        DoctorName.setText(Name);
        DoctorSpecialist.setText("Specialist : "+Specialist);
        DoctorWorking.setText(Working);
        DoctorServicetime.setText("Working time : " +Servicetime);
        DoctorLocation.setText("("+Location +")");
        DoctorRating.setText(Rating);


    }
}
