package com.project.mypetsphuket.Admin.AddNewCustomers;

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
import com.project.mypetsphuket.Model.Hospitals;
import com.project.mypetsphuket.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNewHospitalsActivity extends AppCompatActivity {

    private TextView closeTextBtn, NextTextButton;
    private String CategoryName, Name, Description, Phone, Location, Locationlatitude, Locationlongtitude, Servicetime , Servicetype, saveCurrentDate, saveCurrentTime ,Rating;
    private Button AddNewHospitalButton;
    private ImageView InputHospitalImage;
    private EditText InputName, InputDescription, InputPhone, InputLocation, InputLocationlatitude, InputLocationlongtitude,InputServicetime , InputServicetype,InputRating;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String HospitalRandomKey, downloadImageUrl;
    private StorageReference HospitalImagesRef;
    private DatabaseReference HospitalRef;
    private ProgressBar loadingProgress;
    long maxId=0;
    private String parentDbName;
    Hospitals Hospital;

   // Emergency  AddNewEmergencyActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hospitals);

        parentDbName = "Hospitals";

        CategoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(AddNewHospitalsActivity.this, "This is  " + CategoryName + " ready to add now ", Toast.LENGTH_SHORT).show();
        HospitalImagesRef = FirebaseStorage.getInstance().getReference().child(parentDbName);
        HospitalRef =    FirebaseDatabase.getInstance().getReference().child(parentDbName);

        closeTextBtn = (TextView) findViewById(R.id.close_Hospital_btn);
        InputHospitalImage = (ImageView) findViewById(R.id.select_Hospital_image);
        InputName = (EditText) findViewById(R.id.add_Hospital_name);
        InputDescription = (EditText) findViewById(R.id.add_Hospital_description);
        InputPhone = (EditText) findViewById(R.id.add_Hospital_phone);
        InputLocation = (EditText) findViewById(R.id.add_Hospital_location);
        InputLocationlatitude = (EditText) findViewById(R.id.add_Hospital_location_la);
        InputLocationlongtitude = (EditText) findViewById(R.id.add_Hospital_location_long);
        InputServicetime = (EditText) findViewById(R.id.add_Hospital_servicetime);
        InputServicetype = (EditText) findViewById(R.id.add_Hospital_servicetype);
        InputRating = (EditText) findViewById(R.id.add_Hospital_rating);

        Hospital = new Hospitals();

        AddNewHospitalButton = (Button) findViewById(R.id.add_new_Hospital_btn);
        loadingProgress = findViewById(R.id.Add_Hospital_ProgressBar);


        HospitalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                maxId=(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        InputHospitalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenGallery();
            }
        });

        AddNewHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidateHospitalData();


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
        super.onActivityResult(requestCode, resultCode, data); //new

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {

            ImageUri = data.getData();
            InputHospitalImage.setImageURI(ImageUri);
        }
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);

    }

    private void ValidateHospitalData() {

        Name = InputName.getText().toString();
        Phone = InputPhone.getText().toString();
        Description = InputDescription.getText().toString();
        Location = InputLocation.getText().toString();
        Locationlatitude = InputLocationlatitude.getText().toString();
        Locationlongtitude = InputLocationlongtitude.getText().toString();
        Servicetype = InputServicetype.getText().toString();
        Servicetime = InputServicetype.getText().toString();
        Rating = InputRating.getText().toString();

        AddNewHospitalButton.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);

        if (ImageUri == null) {

            AddNewHospitalButton.setVisibility(View.INVISIBLE);
            loadingProgress.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Hospital image is mandatory !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);


        } else if (TextUtils.isEmpty(Name)) {

            Toast.makeText(this, "Please write Hospital name !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Phone)) {

            Toast.makeText(this, "Please write Hospital Phone number !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Description)) {

            Toast.makeText(this, "Please write Hospital description !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Location)) {

            Toast.makeText(this, "Please write Hospital location !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlatitude)) {

            Toast.makeText(this, "Please write  Hospital Location latitude !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlongtitude)) {

            Toast.makeText(this, "Please  place Hospital Location longtitude !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetime)) {

            Toast.makeText(this, "Please write Service time !", Toast.LENGTH_SHORT).show();
            AddNewHospitalButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetype)) {

        Toast.makeText(this, "Please write Service type !", Toast.LENGTH_SHORT).show();
        AddNewHospitalButton.setVisibility(View.VISIBLE);

         } else {

            StoreHospitalInformation();
        }

    }

    private void StoreHospitalInformation() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a ");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HospitalRandomKey = saveCurrentDate + " " + saveCurrentTime;

        final StorageReference filePath = HospitalImagesRef.child(ImageUri.getLastPathSegment() + HospitalRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AddNewHospitalsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                AddNewHospitalButton.setVisibility(View.VISIBLE);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AddNewHospitalsActivity.this, "Hospital Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                AddNewHospitalButton.setVisibility(View.VISIBLE);

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

                            Toast.makeText(AddNewHospitalsActivity.this, "got the Hospital image Url Successfully", Toast.LENGTH_SHORT).show();
                            AddNewHospitalButton.setVisibility(View.VISIBLE);

                            SaveHospitalInfoToDatabase();
                        }
                    }
               });
            }
        });
    }

    private void SaveHospitalInfoToDatabase() {

        HashMap<String, Object> HospitalMap = new HashMap<>();
     //   HospitalMap.put("pid", HospitalRandomKey);


        HospitalMap.put("name", Name);
        HospitalMap.put("id", String.valueOf(maxId+1));
        HospitalMap.put("category", CategoryName);
        HospitalMap.put("phone", Phone);
        HospitalMap.put("description", Description);
        HospitalMap.put("location", Location);
        HospitalMap.put("locationlatitude", Locationlatitude);
        HospitalMap.put("locationlongtitude", Locationlongtitude);
        HospitalMap.put("servicetime", Servicetime);
        HospitalMap.put("servicetype", Servicetype);
        HospitalMap.put("rating", Rating);
        HospitalMap.put("url", downloadImageUrl);

        HospitalRef.child(/*CategoryName + */String.valueOf(maxId+1)).setValue(HospitalMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                /*    Intent intent = new Intent(AddNewHospitalsActivity.this, AdminCategoryActivity.class);
                    startActivity(intent);   */
                    Toast.makeText(AddNewHospitalsActivity.this, "Hospital is added successfully", Toast.LENGTH_SHORT).show();
                    AddNewHospitalButton.setVisibility(View.VISIBLE);

                } else {

                    String message = task.getException().toString();
                    Toast.makeText(AddNewHospitalsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    AddNewHospitalButton.setVisibility(View.VISIBLE);


                }
            }


        });

    }


}




