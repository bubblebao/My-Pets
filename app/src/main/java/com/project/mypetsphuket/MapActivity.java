package com.project.mypetsphuket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.mypetsphuket.Prevalent.Decryption;
import com.project.mypetsphuket.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private String  ImageView, Id ,Tittle ,Name, Decryption, Working, Servicetime, Location , Servicetype , Rating;
    private String Latitude , Longtitude;

    private TextView  MapTitle , MapLocation;
    private TextView  closeTextBtn;


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //1. Find Object in activity_map
        MapTitle = (TextView) findViewById(R.id.title_googleMap);
        MapLocation = (TextView) findViewById(R.id.location_Map);


        closeTextBtn = (TextView) findViewById(R.id.close_Map_btn);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // 2. Get Data form Detail Activity
        ReceiveDataFormDetailActivity();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

             //Latitude = "7.859737" ;
             //Longtitude = "98.341187";


            MapTitle.setText(Name);
            MapLocation.setText(Location);

             mMap = googleMap;

             double lat = Double.parseDouble(Latitude);
             double lng = Double.parseDouble(Longtitude);

             if (Tittle.equals("Doctors")){
                 // Add a marker in Sydney, Australia, and move the camera.
                 LatLng Mypet = new LatLng(lat,lng);
                 mMap.addMarker(new MarkerOptions().position(Mypet).title(Working).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_app_marker)));
                 mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mypet , 18) , 2000 , null);    //Scope x 18 , Speed much Low

             }else {

                 // Add a marker in Sydney, Australia, and move the camera.
                 LatLng Mypet = new LatLng(lat,lng);
                 mMap.addMarker(new MarkerOptions().position(Mypet).title(Name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_app_marker)));
                 mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mypet , 18) , 2000 , null);    //Scope x 18 , Speed much Low

             }

    }


    private void ReceiveDataFormDetailActivity() {

        // Receive Data form  Detail Activity
        Intent intent = getIntent();

        //Id = intent.getStringExtra("Id");
        Tittle = intent.getStringExtra("title");
        ImageView = intent.getStringExtra("Url");
        Name = intent.getStringExtra("Name");
        Decryption = intent.getStringExtra("Description");
        Location = intent.getStringExtra("Location");
        Servicetime = intent.getStringExtra("Servicetime");
        Rating = intent.getStringExtra("Rating");
        Working = intent.getStringExtra("Working");
        Latitude = intent.getStringExtra("Latitude");
        Longtitude = intent.getStringExtra("Longtitude");


    }
}
