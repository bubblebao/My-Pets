package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.project.mypetsphuket.Model.Hospitals;
import com.project.mypetsphuket.Model.Images;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.RecyclerAdepter;
import com.project.mypetsphuket.ViewHolder.HospitalViewHolder;

import java.util.ArrayList;

import io.paperdb.Paper;

public class HospitalsActivity extends AppCompatActivity {

    private TextView closeTextBtn ;
    private static final String TAG = "HospitalsActivity";

    //Firebase
    private DatabaseReference reference;
    private StorageReference mStorageRef;

    //widgets
    private Context mcontext = HospitalsActivity.this;
    private RecyclerView recyclerView;

    //Vars
    private ArrayList<Images> imagesList;
    private RecyclerAdepter recyclerAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        closeTextBtn = (TextView) findViewById(R.id.close_hospitals_btn);
        //NextTextButon = (TextView) findViewById(R.id.hospitals_next_btn);
        Log.d(TAG,"onCreate started");

        //  hDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Hospitals");
        //   ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


           // Paper.init(this);

        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        imagesList = new ArrayList<>();

        init();


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
     //   showData();

    }

    private void init() {

        clearAll();

        Query query = reference.child("Images");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){

                    Images images = new Images();

                    images.setUrl(snapshot.child("url").getValue().toString());
                    images.setName(snapshot.child("name").getValue().toString());
                    images.setDescription(snapshot.child("description").getValue().toString());
                    images.setLocation(snapshot.child("location").getValue().toString());

                    imagesList.add(images);
                }

                recyclerAdepter = new RecyclerAdepter(mcontext,imagesList);
                recyclerView.setAdapter(recyclerAdepter);
                recyclerAdepter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });

    }

    private void clearAll() {

        if (imagesList != null){

            imagesList.clear();
            if (recyclerAdepter!= null){

                recyclerAdepter.notifyDataSetChanged();
            }
        }

        imagesList = new ArrayList<>();

    }


}

/*
      @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerOptions<Hospitals> options = new FirebaseRecyclerOptions.Builder<Hospitals >()
                .setQuery(HospitalsRef,Hospitals.class)
                .build();

        FirebaseRecyclerAdapter<Hospitals , HospitalViewHolder> adapter =
                new FirebaseRecyclerAdapter<Hospitals, HospitalViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull HospitalViewHolder holder, int position, @NonNull Hospitals model) {

                        holder.txtHospitalName.setText(model.getName());
                        holder.txtHospitalDescription.setText(model.getDescription());
                        holder.txtHospitalLocation.setText(model.getLocation());
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_items_layout , parent,false  );
                        HospitalViewHolder holder = new HospitalViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }  */



