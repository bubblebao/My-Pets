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
import com.project.mypetsphuket.Model.Emergencys;  //here
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.EmerRecyclerAdepter;

import java.util.ArrayList;

public class EmergencyActivity extends AppCompatActivity {
    private TextView closeTextBtn , NextTextButton;
    private static final String TAG = "EmergencyActivity";

    //Firebase
    private DatabaseReference reference;
    private StorageReference mStorageRef;

    //widgets
    private Context mcontext = EmergencyActivity.this;
    private RecyclerView recyclerView;

    //Vars
    private ArrayList<Emergencys> emergencysList;
    private EmerRecyclerAdepter recyclerAdepter;       //here

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

        Log.d(TAG,"onCreate started");

        recyclerView = (RecyclerView) findViewById(R.id.EmerRecycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        emergencysList = new ArrayList<>();

        init();

    }

    private void init() {

        clearAll();

        Query query = reference.child("Emergencys");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){

                    Emergencys emergencys = new Emergencys();

                    emergencys.setUrl(snapshot.child("url").getValue().toString());
                    emergencys.setName(snapshot.child("name").getValue().toString());
                    emergencys.setDescription(snapshot.child("description").getValue().toString());
                    emergencys.setLocation(snapshot.child("location").getValue().toString());
                    emergencys.setRating(snapshot.child("rating").getValue().toString());

                    emergencysList.add(emergencys);
                }

                recyclerAdepter = new EmerRecyclerAdepter(mcontext,emergencysList);   //here
                recyclerView.setAdapter(recyclerAdepter);
                recyclerAdepter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });
    }

    private void clearAll() {

        if (emergencysList != null){

            emergencysList.clear();
            if (recyclerAdepter!= null){

                recyclerAdepter.notifyDataSetChanged();
            }
        }

        emergencysList = new ArrayList<>();

    }
}
