package com.project.mypetsphuket.DelailsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.Model.Informations;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

public class InformationsActivity extends AppCompatActivity {

    private ItemClickListner itemClickListner;
    private ImageView InformationImageView;
    private TextView InformationService;
    private TextView  InformationPhoneNumber;
    private TextView  InformationName;
    private TextView  InformationDescription;
    private TextView  InformationServicetime;
    private TextView  InformationLocation;
    private TextView  InformationRating;
    private ImageView InformationStar1;
    private ImageView InformationStar2;
    private ImageView InformationStar3;
    private ImageView InformationStar4;
    private ImageView InformationStar5;
    private TextView  closeTextBtn;
    private TextView InformationCall;

    private String ImageView , Name , Description , Servicetime , Location , Servicetype ,Rating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        closeTextBtn = (TextView) findViewById(R.id.close_aboutus_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // 9. Find Object in activity_Informations_detail
        InformationImageView = (ImageView) findViewById(R.id.Information_ImageView);
        InformationName = (TextView) findViewById(R.id.Information_Name);
        InformationDescription = (TextView) findViewById(R.id.Information_Description);
        InformationServicetime = (TextView) findViewById(R.id.Information_Servicetime);
        InformationLocation = (TextView) findViewById(R.id.Information_Location);
        InformationService = (TextView) findViewById(R.id.Information_Detial_Service);

        InformationStar1 = (ImageView) findViewById(R.id.Infor_Star1);
        InformationStar2 = (ImageView) findViewById(R.id.Infor_Star2);
        InformationStar3 = (ImageView) findViewById(R.id.Infor_Star3);
        InformationStar4 = (ImageView) findViewById(R.id.Infor_Star4);
        InformationStar5 = (ImageView) findViewById(R.id.Infor_Star5);


        ///Save Data to Informations
        SaveInformation();

        //Display Data to activity_hospitals_detail


    }

    private void ShowInformations() {
        Picasso.get().load(ImageView).into(InformationImageView);
        InformationName.setText(Name);
        InformationDescription.setText(Description);
        InformationServicetime.setText(Servicetime);
        InformationLocation.setText(Location);
        InformationService.setText(Servicetype);
        //  HospitalRating.setText(Rating);

    }


    private void SaveInformation() {

         Informations informationsData = new Informations();
         ImageView = informationsData.getUrl();
         Name = informationsData.getName();
         Description = informationsData.getDescription();
         Location = informationsData.getLocation();
         Servicetime = informationsData.getServicetime();
         Servicetype = informationsData.getServicetype();
         Rating = informationsData.getRating();

        ShowInformations();
    }
}
