package com.project.mypetsphuket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import com.google.android.material.navigation.NavigationView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.mypetsphuket.MenuActivity.AppointmentsActivity;
import com.project.mypetsphuket.MenuActivity.DoctorsActivity;
import com.project.mypetsphuket.MenuActivity.EmergencyActivity;
import com.project.mypetsphuket.MenuActivity.HospitalsActivity;
import com.project.mypetsphuket.MenuActivity.MyAppointmentsActivity;
import com.project.mypetsphuket.MenuActivity.PetshopActivity;
import com.project.mypetsphuket.Model.BookingDoctor;
import com.project.mypetsphuket.Model.Rating;
import com.project.mypetsphuket.Model.Users;
import com.project.mypetsphuket.Prevalent.Encryption;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

 //   GridLayout widget GridLayout;
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Paper.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

          GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);

          setSingleEvent(mainGrid);
  /*      FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });       */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        //String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        //userNameTextView.setText(UserPhoneKey);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        userNameTextView.setText(Prevalent.currentOnlineUser.getName());
        Picasso.get().load(Prevalent.currentOnlineUser.getUrl()).placeholder(R.drawable.profile).into(profileImageView);

      //  ReceiveRatingData();
      //  checkRatingDialog();

    }

    @Override
    protected void onResume() {
        super.onResume();

    //    ReceiveRatingData();
        ReceiveRatingDataFireStore();
  //      checkRatingDialog();
//        checkRatingDialog();
    }

    private void ReceiveRatingDataFireStore() {

        Paper.init(this);
        DocumentReference ratingRef;


        ratingRef = FirebaseFirestore.getInstance()
                .collection("User")
                .document(Prevalent.currentOnlineUser.getPhone())
                .collection("Booking")
                .document(Prevalent.simpleDataFormat.format(Prevalent.bookingDate.getTime()));

        ratingRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    private static final String TAG = "TAG";

                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        ArrayList<Rating> ratingDoctors = new ArrayList<>();
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                Rating ratingDoctor = document.toObject(Rating.class);
                                ratingDoctors.add(ratingDoctor);

                                Paper.init(HomeActivity.this);

                                Paper.book().write(Prevalent.RATING_INFORMATION_KEY ,document.getId());

                                Prevalent.currentRating = ratingDoctor;
                                Log.d(TAG , "ratingState " + Prevalent.currentRating.getRatingState());
                                Log.d(TAG , "Rating Status:" + Prevalent.currentRating.isDone());
                                Log.d(TAG, "DocumentSnapshot Rating: " + document.getData());
                                Log.d(TAG, "rating ID : " + document.getId());

                                //Show false = New Rating
                                if (!Prevalent.currentRating.isDone()){

                                    checkRatingDialog();
                                }





                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeActivity.this ,""+e.getMessage(), Toast.LENGTH_SHORT ).show();

            }
        });
    }

    private void checkRatingDialog() {
        Paper.init(this);
      //  String dataSerialized = Prevalent.RATING_INFORMATION_KEY;
       String dataSerialized = Paper.book().read(Prevalent.RATING_INFORMATION_KEY );
       Rating ratingData = new Rating();


        if (!TextUtils.isEmpty(dataSerialized)){
            if ( ratingData != null){

                Prevalent.showRatingDialog(HomeActivity.this,
                        Prevalent.currentRating.getRatingState(),
                        Prevalent.currentRating.getHospitalID(),
                        Prevalent.currentRating.getHospitalName(),
                        Prevalent.currentRating.getDoctorID());
            }
        }
    }

    private void ReceiveRatingData() {

        DatabaseReference mDatabase =FirebaseDatabase.getInstance()
                .getReference("Rating")
                .child(Prevalent.simpleDataFormat.format(Prevalent.bookingDate.getTime()));

        mDatabase.orderByChild("customerPhone").equalTo(Prevalent.currentOnlineUser.getPhone())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                        ///Here you can get all data
                        Rating RatingsData = dataSnapshot.getValue(Rating.class);



                   //     Paper.init(HomeActivity.this);
                    //    Paper.book().write(Prevalent.RATING_INFORMATION_KEY ,RatingsData);
                        Prevalent.currentRating = RatingsData;
                      //  Log.d("TAG RatingsData" , "onChildAdded:" + dataSnapshot.getKey());
                        Toast.makeText(HomeActivity.this,"Success loaded RatingsData !",Toast.LENGTH_SHORT).show();


                    Log.d("TAG" , "onChild:" + dataSnapshot.getKey());
                    Log.d("TAG" , "ratingState " + Prevalent.currentRating.getRatingState());
                    Log.d("TAG" , "doctorID:" + Prevalent.currentRating.getDoctorID());



                }else {
                    Toast.makeText(HomeActivity.this, "Please try again load RatingsData ", Toast.LENGTH_LONG).show();

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

/*
        DatabaseReference mDatabase =FirebaseDatabase.getInstance()
                .getReference("Rating")
                .child(Prevalent.simpleDataFormat.format(Prevalent.bookingDate.getTime()));

        mDatabase.orderByChild("customerPhone").equalTo(Prevalent.currentOnlineUser.getPhone());


        final DatabaseReference RatingRef;
        RatingRef = FirebaseDatabase.getInstance()
                .getReference().child("Rating").child(Prevalent.simpleDataFormat.format(Prevalent.bookingDate.getTime())); //.child(Prevalent.currentOnlineUser.getPhone())

        RatingRef.orderByChild("customerPhone").equalTo(Prevalent.currentOnlineUser.getPhone()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){

                        Rating RatingsData = dataSnapshot.getValue(Rating.class);
                        Paper.init(HomeActivity.this);
                        Paper.book().write(Prevalent.RATING_INFORMATION_KEY ,RatingsData);

                        Prevalent.currentRating = RatingsData;


                        Log.d("TAG" , "onChild:" + dataSnapshot.getKey());
                    Log.d("TAG" , "ratingState " + RatingsData.getRatingState());
                    Log.d("TAG" , "doctorID:" + RatingsData.getRatingState());

                    Toast.makeText(HomeActivity.this,"Success loaded RatingsData !",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(HomeActivity.this, "Error to load Rating Data.", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

*/
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid

        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //DoctorsActivity
                    if (finalI == 0){
                        Intent intent = new Intent(HomeActivity.this, DoctorsActivity.class);
                        //intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }

                    else if (finalI == 1){
                        Intent intent = new Intent(HomeActivity.this, HospitalsActivity.class);
                        //intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI == 2){
                        Intent intent = new Intent(HomeActivity.this, PetshopActivity.class);
                        //intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI == 3){
                        Intent intent = new Intent(HomeActivity.this, AppointmentsActivity.class);
                        //intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI == 4){
                        Intent intent = new Intent(HomeActivity.this, EmergencyActivity.class);
                        //intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI == 5){
                        Intent intent = new Intent(HomeActivity.this, MyAppointmentsActivity.class);
                        //intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }


                }
            });
        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }                    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
          //  startActivity(intent);

        }
        else if (id == R.id.nav_doctor) {

            Intent intent = new Intent(HomeActivity.this, DoctorsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_emergancy) {


            Intent intent = new Intent(HomeActivity.this, EmergencyActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_aboutus) {

            Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_setting) {


            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_signout) {

            Paper.book().destroy();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
