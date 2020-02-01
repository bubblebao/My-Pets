package com.project.mypetsphuket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.mypetsphuket.Model.Users;
import com.project.mypetsphuket.Prevalent.Encryption;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.rey.material.widget.CheckBox;

import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button joinNewRutton , loginButton;
    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);



        Dexter.withActivity(this)
                .withPermissions(new String[]{
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_CALENDAR
                }).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                //    String UserEmailKey = Paper.book().read(Prevalent.UserEmailKey);
                String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
                String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

                if (UserPhoneKey != ""&& UserPasswordKey !=""){

                    if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)){

                        AllowAccess(UserPhoneKey,UserPasswordKey);

                        Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();


                    }
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();




        joinNewRutton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        joinNewRutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
//


    }

       private void AllowAccess(final String phone, final String password) {

       final DatabaseReference RootRef ;
       RootRef = FirebaseDatabase.getInstance().getReference();

       parentDbName = Paper.book().read(Prevalent.parentDbNameKey);

       RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.child(parentDbName).child(phone).exists()){

                   Encryption Encryption = new Encryption(password);
                   String passwordEncrypted = null;
                   try {
                       passwordEncrypted = Encryption.getEncryption();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

                   Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                   if (usersData.getPhone().equals(phone)){
                       if (usersData.getPassword().equals(passwordEncrypted)){

                           if (parentDbName.equals("Admins"))
                           {
                               Toast.makeText(MainActivity.this, "Welcome Admin, logged in Successfully", Toast.LENGTH_SHORT).show();
                               // Prevalent.currentOnlineUser = usersData;
                               Intent intent = new Intent(MainActivity.this, AdminCategoryActivity.class);
                               Prevalent.currentOnlineUser = usersData;
                               startActivity(intent);
                           }
                           else if (parentDbName.equals("Users"))
                           {
                               Toast.makeText(MainActivity.this, "logged in Successfully", Toast.LENGTH_SHORT).show();

                               Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                               Prevalent.currentOnlineUser = usersData;
                               startActivity(intent);
                           }
                       }
                       else {
                           Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
               else {
                   Toast.makeText(MainActivity.this, "login fail ", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
}
