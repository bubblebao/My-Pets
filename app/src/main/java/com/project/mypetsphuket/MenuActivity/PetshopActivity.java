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
import com.project.mypetsphuket.Model.Petshops;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.PetshopRecyclerAdepter;

import java.util.ArrayList;

public class PetshopActivity extends AppCompatActivity {

    private TextView closeTextBtn , NextTextButton;

    private static final String TAG = "PetshopActivity";

    //Firebase
    private DatabaseReference reference;
    private StorageReference mStorageRef;

    //widgets
    private Context mcontext = PetshopActivity.this;
    private RecyclerView recyclerView;

    //Vars
    private ArrayList<Petshops> petshopsList;
    private PetshopRecyclerAdepter recyclerAdepter;       //here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petshop);

        closeTextBtn = (TextView) findViewById(R.id.close_petshop_btn);
        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        Log.d(TAG,"onCreate started");

        recyclerView = (RecyclerView) findViewById(R.id.PetshopRecycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        petshopsList = new ArrayList<>();

        init();



    }

    private void init() {
        clearAll();

        Query query = reference.child("Petshops");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){

                    Petshops petshops = new Petshops();

                    petshops.setUrl(snapshot.child("url").getValue().toString());
                    petshops.setName(snapshot.child("name").getValue().toString());
                    petshops.setDescription(snapshot.child("description").getValue().toString());
                    petshops.setLocation(snapshot.child("location").getValue().toString());
                    petshops.setRating(snapshot.child("rating").getValue().toString());

                    petshopsList.add(petshops);
                }

                recyclerAdepter = new PetshopRecyclerAdepter(mcontext,petshopsList);   //here
                recyclerView.setAdapter(recyclerAdepter);
                recyclerAdepter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void clearAll() {

        if (petshopsList != null){

            petshopsList.clear();
            if (recyclerAdepter!= null){

                recyclerAdepter.notifyDataSetChanged();
            }
        }

        petshopsList = new ArrayList<>();

    }
}
