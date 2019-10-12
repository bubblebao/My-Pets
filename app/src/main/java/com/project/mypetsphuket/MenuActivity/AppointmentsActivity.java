package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.mypetsphuket.HomeActivity;
import com.project.mypetsphuket.LoginActivity;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;

import java.util.Arrays;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 7077;
    private TextView   closeTextBtn , NextTextButton;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        closeTextBtn = (TextView) findViewById(R.id.close_appointment_btn);
        NextTextButton = (TextView) findViewById(R.id.appointment_next_btn);


        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                AuthUI.getInstance()
                        .signOut(AppointmentsActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                closeTextBtn.setEnabled(false);
                                showSignInOptions();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AppointmentsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        //init Provider
               providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()   //Email
                  //Phone

               );

          /*
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());   */

          showSignInOptions();
    }

    private void showSignInOptions() {

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .build(),MY_REQUEST_CODE
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE){

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (requestCode == RESULT_OK){

                //get user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //show Email
                Intent intent = new Intent(AppointmentsActivity.this, HomeActivity.class);
                startActivity(intent);
               // Toast.makeText(AppointmentsActivity.this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();

            }else {

             //   Toast.makeText(AppointmentsActivity.this, ""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AppointmentsActivity.this, HomeActivity.class);
                startActivity(intent);

            }

        }
    }
}
