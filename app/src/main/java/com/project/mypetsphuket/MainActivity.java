package com.project.mypetsphuket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.project.mypetsphuket.Model.Users;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button joinNewRutton , loginButton;
    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);


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
        String UserEmailKey = Paper.book().read(Prevalent.UserEmailKey);
        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey != ""&& UserPasswordKey !=""){

            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)){
                
                AllowAccess(UserPhoneKey,UserPasswordKey);

                Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();

                
            }
        }

    }

       private void AllowAccess(final String phone, final String password) {

       final DatabaseReference RootRef ;
       RootRef = FirebaseDatabase.getInstance().getReference();

       parentDbName = Paper.book().read(Prevalent.parentDbNameKey);

       RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.child(parentDbName).child(phone).exists()){

                   Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                   if (usersData.getPhone().equals(phone)){
                       if (usersData.getPassword().equals(password)){

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
