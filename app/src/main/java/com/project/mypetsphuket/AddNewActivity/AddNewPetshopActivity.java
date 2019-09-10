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
import com.project.mypetsphuket.Model.Petshops;
import com.project.mypetsphuket.Model.Petshops;
import com.project.mypetsphuket.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNewPetshopActivity extends AppCompatActivity {

    private TextView closeTextBtn, NextTextButton;
    private String CategoryName, Name, Description, Phone, Location, Locationlatitude, Locationlongtitude, Servicetime , Servicetype, saveCurrentDate, saveCurrentTime , Rating;
    private Button AddNewPetshopButton;
    private ImageView InputPetshopImage;
    private EditText InputName, InputDescription, InputPhone, InputLocation, InputLocationlatitude, InputLocationlongtitude,InputServicetime , InputServicetype , InputRating;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String PetshopRandomKey, downloadImageUrl;
    private StorageReference PetshopImagesRef;
    private DatabaseReference PetshopRef;
    private ProgressBar loadingProgress;
    long maxId=0;
    private String parentDbName;
    Petshops Petshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_petshop);

        parentDbName = "Petshops";

        CategoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(AddNewPetshopActivity.this, "This is  " + CategoryName + " ready to add now ", Toast.LENGTH_SHORT).show();
        PetshopImagesRef = FirebaseStorage.getInstance().getReference().child(parentDbName);
        PetshopRef =    FirebaseDatabase.getInstance().getReference().child(parentDbName);

        closeTextBtn = (TextView) findViewById(R.id.close_Petshop_add_btn);
        InputPetshopImage = (ImageView) findViewById(R.id.select_Petshop_image);
        InputName = (EditText) findViewById(R.id.add_Petshop_name);
        InputDescription = (EditText) findViewById(R.id.add_Petshop_description);
        InputPhone = (EditText) findViewById(R.id.add_Petshop_phone);
        InputLocation = (EditText) findViewById(R.id.add_Petshop_location);
        InputLocationlatitude = (EditText) findViewById(R.id.add_Petshop_location_la);
        InputLocationlongtitude = (EditText) findViewById(R.id.add_Petshop_location_long);
        InputServicetime = (EditText) findViewById(R.id.add_Petshop_servicetime);
        InputServicetype = (EditText) findViewById(R.id.add_Petshop_servicetype);
        InputRating = (EditText) findViewById(R.id.add_Petshop_rating);


        Petshop = new Petshops();

        AddNewPetshopButton = (Button) findViewById(R.id.add_new_Petshop_btn);
        loadingProgress = findViewById(R.id.Add_Petshop_ProgressBar);


        PetshopRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                maxId=(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        InputPetshopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenGallery();
            }
        });

        AddNewPetshopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidatePetshopData();


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

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {

            ImageUri = data.getData();
            InputPetshopImage.setImageURI(ImageUri);
        }

    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);

    }

    private void ValidatePetshopData() {
        Name = InputName.getText().toString();
        Phone = InputPhone.getText().toString();
        Description = InputDescription.getText().toString();
        Location = InputLocation.getText().toString();
        Locationlatitude = InputLocationlatitude.getText().toString();
        Locationlongtitude = InputLocationlongtitude.getText().toString();
        Servicetype = InputServicetype.getText().toString();
        Servicetime = InputServicetype.getText().toString();
        Rating = InputRating.getText().toString();

        AddNewPetshopButton.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);

        if (ImageUri == null) {

            Toast.makeText(this, "Petshop image is mandatory !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);


        } else if (TextUtils.isEmpty(Name)) {

            Toast.makeText(this, "Please write Petshop name !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Phone)) {

            Toast.makeText(this, "Please write Petshop Phone number !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Description)) {

            Toast.makeText(this, "Please write Petshop description !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Location)) {

            Toast.makeText(this, "Please write Petshop location !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlatitude)) {

            Toast.makeText(this, "Please write  Petshop Location latitude !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlongtitude)) {

            Toast.makeText(this, "Please  place Petshop Location longtitude !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetime)) {

            Toast.makeText(this, "Please write Service time !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetype)) {

            Toast.makeText(this, "Please write Service type !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Rating)) {

            Toast.makeText(this, "Please Petshop Rating !", Toast.LENGTH_SHORT).show();
            AddNewPetshopButton.setVisibility(View.VISIBLE);

        }else {

            StorePetshopInformation();
        }


    }

    private void StorePetshopInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a ");
        saveCurrentTime = currentTime.format(calendar.getTime());

        PetshopRandomKey = saveCurrentDate + " " + saveCurrentTime;

        final StorageReference filePath = PetshopImagesRef.child(ImageUri.getLastPathSegment() + PetshopRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AddNewPetshopActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                AddNewPetshopButton.setVisibility(View.VISIBLE);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AddNewPetshopActivity.this, "Petshop Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                AddNewPetshopButton.setVisibility(View.VISIBLE);

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

                            Toast.makeText(AddNewPetshopActivity.this, "got the Petshop image Url Successfully", Toast.LENGTH_SHORT).show();
                            AddNewPetshopButton.setVisibility(View.VISIBLE);

                            SavePetshopInfoToDatabase();
                        }
                    }
                });
            }
        });

    }

    private void SavePetshopInfoToDatabase() {

        HashMap<String, Object> PetshopMap = new HashMap<>();
        //   PetshopMap.put("pid", PetshopRandomKey);

        PetshopMap.put("id", String.valueOf(maxId+1));
        PetshopMap.put("url", downloadImageUrl);
        PetshopMap.put("category", CategoryName);
        PetshopMap.put("name", Name);
        PetshopMap.put("phone", Phone);
        PetshopMap.put("description", Description);
        PetshopMap.put("location", Location);
        PetshopMap.put("locationlatitude", Locationlatitude);
        PetshopMap.put("locationlongtitude", Locationlongtitude);
        PetshopMap.put("servicrtime", Servicetime);
        PetshopMap.put("servicrtype", Servicetype);
        PetshopMap.put("rating", Rating);

        PetshopRef.child(CategoryName+String.valueOf(maxId+1)).setValue(PetshopMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AddNewPetshopActivity.this, AdminCategoryActivity.class);
                      /*      startActivity(intent);   */
                            Toast.makeText(AddNewPetshopActivity.this, "Petshop is added successfully", Toast.LENGTH_SHORT).show();
                            AddNewPetshopButton.setVisibility(View.VISIBLE);

                        } else {

                            String message = task.getException().toString();
                            Toast.makeText(AddNewPetshopActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            AddNewPetshopButton.setVisibility(View.VISIBLE);


                        }
                    }


                });

    }


}
