package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.DocRecyclerAdepter;

import java.util.ArrayList;

public class DoctorsActivity extends AppCompatActivity {
    private TextView   closeTextBtn , NextTextButton;
    private static final String TAG = "DoctorsActivity";

    //Firebase
    private DatabaseReference reference;

    //widgets
    private Context mcontext = DoctorsActivity.this;
    private RecyclerView recyclerView;

    //Vars
    private ArrayList<Doctors> doctorsList;
    private DocRecyclerAdepter recyclerAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        closeTextBtn = (TextView) findViewById(R.id.close_doctors_btn);
      //  NextTextButton = (TextView) findViewById(R.id.update_settings_btn);


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        Log.d(TAG,"onCreate started");

        recyclerView = (RecyclerView) findViewById(R.id.DocRecycleView);  //here

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        reference = FirebaseDatabase.getInstance().getReference();


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        doctorsList = new ArrayList<>();

        init();

    }

    private void init() {
        clearAll();

        Query query = reference.child("Doctors");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){

                    Doctors doctors = new Doctors();

                    doctors.setId(snapshot.child("id").getValue().toString());
                    doctors.setUrl(snapshot.child("url").getValue().toString());
                    doctors.setName(snapshot.child("name").getValue().toString());
                    doctors.setSpecialist(snapshot.child("specialist").getValue().toString());
                    doctors.setPhone(snapshot.child("phone").getValue().toString());
                    doctors.setDescription(snapshot.child("description").getValue().toString());
                    doctors.setWorking(snapshot.child("working").getValue().toString());
                    doctors.setLocation(snapshot.child("location").getValue().toString());
                    doctors.setServicetime(snapshot.child("servicetime").getValue().toString());
                    doctors.setCertificate(snapshot.child("certificate").getValue().toString());
                    doctors.setRating(snapshot.child("rating").getValue().toString());

                    doctors.setLocationlatitude(snapshot.child("locationlatitude").getValue().toString());
                    doctors.setLocationlongtitude(snapshot.child("locationlongtitude").getValue().toString());

                    doctorsList.add(doctors);
                }

                recyclerAdepter = new DocRecyclerAdepter(mcontext,doctorsList);
                recyclerView.setAdapter(recyclerAdepter);
                recyclerAdepter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void clearAll() {

        if (doctorsList != null){

            doctorsList.clear();
            if (recyclerAdepter!= null){

                recyclerAdepter.notifyDataSetChanged();
            }
        }

        doctorsList = new ArrayList<>();


    }
}
