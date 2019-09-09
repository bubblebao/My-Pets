package com.project.mypetsphuket.AddNewActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.mypetsphuket.AdminCategoryActivity;
import com.project.mypetsphuket.Model.Emergencys;
import com.project.mypetsphuket.Model.Emergencys;
import com.project.mypetsphuket.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNewEmergencyActivity extends AppCompatActivity {
    private TextView closeTextBtn, NextTextButton;
    private String CategoryName, Name, Description, Phone, Location, Locationlatitude, Locationlongtitude, Servicetime , Servicetype, saveCurrentDate, saveCurrentTime;
    private Button AddNewEmergencyButton;
    private ImageView InputEmergencyImage;
    private EditText InputName, InputDescription, InputPhone, InputLocation, InputLocationlatitude, InputLocationlongtitude,InputServicetime , InputServicetype;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String EmergencyRandomKey, downloadImageUrl;
    private StorageReference EmergencyImagesRef;
    private DatabaseReference EmergencyRef;
    private ProgressBar loadingProgress;
    long maxId=0;
    private String parentDbName;
    Emergencys Emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_emergency);

        parentDbName = "Emergencys";

        CategoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(AddNewEmergencyActivity.this, "This is  " + CategoryName + " ready to add now ", Toast.LENGTH_SHORT).show();
        EmergencyImagesRef = FirebaseStorage.getInstance().getReference().child(parentDbName);
        EmergencyRef =    FirebaseDatabase.getInstance().getReference().child(parentDbName);

        closeTextBtn = (TextView) findViewById(R.id.close_Emergency_btn);
        InputEmergencyImage = (ImageView) findViewById(R.id.select_Emergency_image);
        InputName = (EditText) findViewById(R.id.add_Emergency_name);
        InputDescription = (EditText) findViewById(R.id.add_Emergency_description);
        InputPhone = (EditText) findViewById(R.id.add_Emergency_phone);
        InputLocation = (EditText) findViewById(R.id.add_Emergency_location);
        InputLocationlatitude = (EditText) findViewById(R.id.add_Emergency_location_la);
        InputLocationlongtitude = (EditText) findViewById(R.id.add_Emergency_location_long);
        InputServicetime = (EditText) findViewById(R.id.add_Emergency_servicetime);
        InputServicetype = (EditText) findViewById(R.id.add_Emergency_servicetype);

        Emergency = new Emergencys();

        AddNewEmergencyButton = (Button) findViewById(R.id.add_new_Emergency_btn);
        loadingProgress = findViewById(R.id.add_Emergency_ProgressBar);


        EmergencyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                maxId=(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        InputEmergencyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenGallery();
            }
        });

        AddNewEmergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidateEmergencyData();
                AddNewEmergencyButton.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);

            }
        });

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {

            ImageUri = data.getData();
            InputEmergencyImage.setImageURI(ImageUri);
        }
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    private void ValidateEmergencyData() {
        Name = InputName.getText().toString();
        Phone = InputPhone.getText().toString();
        Description = InputDescription.getText().toString();
        Location = InputLocation.getText().toString();
        Locationlatitude = InputLocationlatitude.getText().toString();
        Locationlongtitude = InputLocationlongtitude.getText().toString();
        Servicetype = InputServicetype.getText().toString();
        Servicetime = InputServicetype.getText().toString();

        if (ImageUri == null) {

            Toast.makeText(this, "Emergency image is mandatory !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);


        } else if (TextUtils.isEmpty(Name)) {

            Toast.makeText(this, "Please write Emergency name !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Phone)) {

            Toast.makeText(this, "Please write Emergency Phone number !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Description)) {

            Toast.makeText(this, "Please write Emergency description !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Location)) {

            Toast.makeText(this, "Please write Emergency location !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlatitude)) {

            Toast.makeText(this, "Please write  Emergency Location latitude !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlongtitude)) {

            Toast.makeText(this, "Please  place Emergency Location longtitude !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetime)) {

            Toast.makeText(this, "Please write Service time !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetype)) {

            Toast.makeText(this, "Please write Service type !", Toast.LENGTH_SHORT).show();
            AddNewEmergencyButton.setVisibility(View.VISIBLE);

        } else {

            StoreEmergencyInformation();
        }
    }

    private void StoreEmergencyInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a ");
        saveCurrentTime = currentTime.format(calendar.getTime());

        EmergencyRandomKey = saveCurrentDate + " " + saveCurrentTime;

        final StorageReference filePath = EmergencyImagesRef.child(ImageUri.getLastPathSegment() + EmergencyRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AddNewEmergencyActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                AddNewEmergencyButton.setVisibility(View.VISIBLE);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AddNewEmergencyActivity.this, "Emergency Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                AddNewEmergencyButton.setVisibility(View.INVISIBLE);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AddNewEmergencyActivity.this, "got the Emergency image Url Successfully", Toast.LENGTH_SHORT).show();
                            AddNewEmergencyButton.setVisibility(View.VISIBLE);

                            SaveEmergencyInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveEmergencyInfoToDatabase() {

        HashMap<String, Object> EmergencyMap = new HashMap<>();
        //   EmergencyMap.put("pid", EmergencyRandomKey);

  //      EmergencyMap.put("id", EmergencyRandomKey);
        EmergencyMap.put("image", downloadImageUrl);
        EmergencyMap.put("category", CategoryName);
        EmergencyMap.put("name", Name);
        EmergencyMap.put("phone", Phone);
        EmergencyMap.put("description", Description);
        EmergencyMap.put("location", Location);
        EmergencyMap.put("locationlatitude", Locationlatitude);
        EmergencyMap.put("locationlongtitude", Locationlongtitude);
        EmergencyMap.put("servicrtime", Servicetime);
        EmergencyMap.put("servicrtype", Servicetype);

        EmergencyRef.child(String.valueOf(maxId+1)).setValue(EmergencyMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AddNewEmergencyActivity.this, AdminCategoryActivity.class);
                            startActivity(intent);
                            Toast.makeText(AddNewEmergencyActivity.this, "Emergency is added successfully", Toast.LENGTH_SHORT).show();
                            AddNewEmergencyButton.setVisibility(View.VISIBLE);

                        } else {

                            String message = task.getException().toString();
                            Toast.makeText(AddNewEmergencyActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            AddNewEmergencyButton.setVisibility(View.VISIBLE);


                        }
                    }


                });

    }
}
