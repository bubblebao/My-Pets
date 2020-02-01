package com.project.mypetsphuket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.mypetsphuket.Model.Users;
import com.project.mypetsphuket.Prevalent.Encryption;
import com.project.mypetsphuket.Prevalent.PasswordEncryption;
import com.project.mypetsphuket.Prevalent.Prevalent;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private String parentDbName ;
    //Text
    private EditText InputPhoneNumber, InputPassword;
    private TextView AdminLink, NotAdminLink;
    //Button
    private Button LoginButton;
    private ProgressBar loadingProgress;

    private CheckBox rememberme_ckb;

    public LoginActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        parentDbName = "Users";
        //Set Button
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_input);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        LoginButton  = (Button) findViewById(R.id.login_btn);
        loadingProgress = findViewById(R.id.regProgressBar);

        rememberme_ckb = (CheckBox) findViewById(R.id.rememberme_Chk);

        //Init Paper
        Paper.init(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     LoginButton.setVisibility(View.INVISIBLE);
          //      loadingProgress.setVisibility(View.VISIBLE);
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });

    }

    private void LoginUser() {

        String phone = InputPhoneNumber.getText().toString();
    //    String email = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();

        LoginButton = (Button) findViewById(R.id.login_btn);
        loadingProgress = findViewById(R.id.loginProgressBar);



        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();


        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();


        } else {

            LoginButton.setVisibility(View.INVISIBLE);
            loadingProgress.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Please wait, We are checking login Account", Toast.LENGTH_SHORT).show();

            AllowAccessToAccount(phone , password , parentDbName);
            //AllowAccessToAccount(phone, email, password , parentDbName);

        }
    }

  //  private void AllowAccessToAccount(final String phone, final String email, final String password,final String parentDb) {
    private void AllowAccessToAccount(final String phone , final String password,final String parentDb) {

        if(rememberme_ckb.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
        //    Paper.book().write(Prevalent.UserEmailKey, email);
            Paper.book().write(Prevalent.UserPasswordKey, password);
            Paper.book().write(Prevalent.parentDbNameKey, parentDb);
        }



        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbName).child(phone).exists()){

                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    Encryption Encryption = new Encryption(password);
                    String passwordEncrypted = null;
                    try {
                        passwordEncrypted = Encryption.getEncryption();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Prevalent.currentOnlineUser.setPassword(passwordEncrypted);
                    if (usersData.getPhone().equals(phone)){
                        if (usersData.getPassword().equals(passwordEncrypted)){

                            if (parentDbName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this, "Welcome Admin, logged in Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                                LoginButton.setVisibility(View.VISIBLE);
                            }
                            else if (parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "logged in Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                                LoginButton.setVisibility(View.VISIBLE);
                            }

                        }
                        else{

                            Toast.makeText(LoginActivity.this, "Password of this " + phone +" incorrect.", Toast.LENGTH_SHORT).show();
                            LoginButton.setVisibility(View.VISIBLE);

                        }
                    }
                }
                else
                {

                    Toast.makeText(LoginActivity.this, "Account with this " + phone + " do not exists.", Toast.LENGTH_SHORT).show();
                    LoginButton.setVisibility(View.VISIBLE);
                  //  Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                  //  startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
