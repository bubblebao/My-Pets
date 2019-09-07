package com.project.mypetsphuket.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.mypetsphuket.R;

import io.paperdb.Paper;

public class HospitalsActivity extends AppCompatActivity {

    private TextView closeTextBtn , NextTextButton;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference HospitalsRef ,ProductsRef ;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        closeTextBtn = (TextView) findViewById(R.id.close_hospitals_btn);
        //NextTextButton = (TextView) findViewById(R.id.hospitals_next_btn);

        HospitalsRef = FirebaseDatabase.getInstance().getReference().child("Hospitals");

   //     ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        Paper.init(this);
        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);
   /*     recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);  */


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });


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


}
