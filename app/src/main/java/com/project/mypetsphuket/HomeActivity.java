package com.project.mypetsphuket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import com.google.android.material.navigation.NavigationView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.mypetsphuket.MenuActivity.AppointmentsActivity;
import com.project.mypetsphuket.MenuActivity.DoctorsActivity;
import com.project.mypetsphuket.MenuActivity.EmergencyActivity;
import com.project.mypetsphuket.MenuActivity.HospitalsActivity;
import com.project.mypetsphuket.MenuActivity.MyAppointmentsActivity;
import com.project.mypetsphuket.MenuActivity.PetshopActivity;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.squareup.picasso.Picasso;



import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;


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
        userNameTextView.setText(Prevalent.currentOnlineUser.getName());
        Picasso.get().load(Prevalent.currentOnlineUser.getUrl()).placeholder(R.drawable.profile).into(profileImageView);




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
