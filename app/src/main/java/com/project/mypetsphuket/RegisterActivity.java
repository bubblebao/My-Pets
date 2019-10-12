package com.project.mypetsphuket;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.mypetsphuket.Prevalent.Encryption;
import com.project.mypetsphuket.Prevalent.PasswordEncryption;
import com.project.mypetsphuket.Prevalent.PasswordEncrytion;

import java.util.HashMap;
import java.util.concurrent.Delayed;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputEmail , InputPhoneNumber, InputPassword;
    private ProgressBar loadingProgress;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton  = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputEmail = (EditText) findViewById(R.id.register_email_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        loadingProgress = findViewById(R.id.regProgressBar);

        //loadingProgress INVISIBLE
        loadingProgress.setVisibility(View.INVISIBLE);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                CreateAccountButton.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);

                 CreateAccount();

            }

        });
    }

    private void CreateAccount() {

        final String name = InputName.getText().toString().trim();
        final String email = InputEmail.getText().toString().trim();
        final String phone = InputPhoneNumber.getText().toString().trim();
        final String password = InputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
            CreateAccountButton.setVisibility(View.VISIBLE);
            //loadingProgress.setVisibility(View.GONE);

        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please write your email", Toast.LENGTH_SHORT).show();
            CreateAccountButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
            CreateAccountButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
            CreateAccountButton.setVisibility(View.VISIBLE);

        } else {

          /*  loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();             */

            ValidatephoneNumber(name, email , phone, password);


        }
    }

    private void ValidatephoneNumber(final String name, final String email, final String phone, final String password)
    {
        final DatabaseReference RootRef;
      //  final int toastDurationInMilliSeconds = 10000;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {


                    HashMap<String, Object> userdataMap = new HashMap<>();

                    //Set to Save
                    String default_profile = ("https://firebasestorage.googleapis.com/v0/b/my-pets-phuket-bb27e.appspot.com/o/Profile%20pictures%2Fprofile.png?alt=media&token=d4443ee6-a88a-4b5b-afaf-2ef928584c2b");
                    //Encryption Password

                    Encryption Encryption = new Encryption(password);
                    String passwordEncrypted = null;
                    try {
                        passwordEncrypted = Encryption.getEncryption();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                  //   PasswordEncryption passwordEncryption = new PasswordEncryption(password);
                 //   String passwordEncrypted = passwordEncryption.getEncryption();

                    //Set to Save
                    userdataMap.put("name", name);
                    userdataMap.put("email", email);
                    userdataMap.put("phone", phone);
                    userdataMap.put("address", "Thailand");
                    userdataMap.put("url", default_profile);
                    userdataMap.put("password", passwordEncrypted);

                    // Save User Data
                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {

                                //onComplete
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                  if (task.isSuccessful()){

                                      Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                      CreateAccountButton.setVisibility(View.VISIBLE);

                                      Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                      startActivity(intent);
                                  }
                                  else {

                                      Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time", Toast.LENGTH_SHORT).show();
                                      CreateAccountButton.setVisibility(View.VISIBLE);

                                  }
                                }

                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This "+  phone +" already exists and , Please try again using another phone number ", Toast.LENGTH_LONG).show();

                //Loading
                    CreateAccountButton.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.GONE);
                //    Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();
                //     Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                //    startActivity(intent);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}