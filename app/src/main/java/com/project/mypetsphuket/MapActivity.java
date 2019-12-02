package com.project.mypetsphuket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.mypetsphuket.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            // Add a marker in Sydney, Australia, and move the camera.
            LatLng sydney = new LatLng(7.888367 , 98.381493);

            // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_doctor_load)));
            //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_doc)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney , 18) ,2500 , null);    //Scope x 18 , Speed much Low

    }
}
