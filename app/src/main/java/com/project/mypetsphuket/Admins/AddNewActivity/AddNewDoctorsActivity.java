package com.project.mypetsphuket.Admins.AddNewActivity;

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
import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNewDoctorsActivity extends AppCompatActivity {

    private TextView closeTextBtn, NextTextButton;
    private String CategoryName, Name, Phone , Working , Location, Locationlatitude, Locationlongtitude,Servicetime, Specialist , saveCurrentDate, saveCurrentTime,Rating;;
    private Button AddNewDoctorButton;
    private ImageView InputDoctorImage;
    private EditText InputName, InputDescription, InputPhone, InputLocation, InputLocationlatitude, InputLocationlongtitude, InputServicetime ,InputServicetype,InputRating;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String DoctorRandomKey, downloadImageUrl;
    private StorageReference DoctorImagesRef;
    private DatabaseReference DoctorRef;
    private ProgressBar loadingProgress;
    long maxId=0;
    private String parentDbName;

    Doctors Doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_doctors);


        parentDbName = "Doctors";


        CategoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(AddNewDoctorsActivity.this, "This is  " + CategoryName + " ready to add now ", Toast.LENGTH_SHORT).show();
        DoctorImagesRef = FirebaseStorage.getInstance().getReference().child(parentDbName);
        DoctorRef =    FirebaseDatabase.getInstance().getReference().child(parentDbName);

        closeTextBtn = (TextView) findViewById(R.id.close_add_Doctor_btn);
        InputDoctorImage = (ImageView) findViewById(R.id.select_Doctor_image);
        InputName = (EditText) findViewById(R.id.add_Doctor_name);
        InputDescription = (EditText) findViewById(R.id.add_Doctor_description);
        InputPhone = (EditText) findViewById(R.id.add_Doctor_phone);
        InputLocation = (EditText) findViewById(R.id.add_Doctor_location);
        InputLocationlatitude = (EditText) findViewById(R.id.add_Doctor_location_la);
        InputLocationlongtitude = (EditText) findViewById(R.id.add_Doctor_location_long);
        InputServicetime = (EditText) findViewById(R.id.add_Doctor_servicetime);
        InputServicetype = (EditText) findViewById(R.id.add_Doctor_specialist);
        InputRating = (EditText) findViewById(R.id.add_Doctor_rating);


        Doctor = new Doctors();

        AddNewDoctorButton = (Button) findViewById(R.id.add_new_Doctor_btn);
        loadingProgress = findViewById(R.id.Add_Doctor_ProgressBar);

        DoctorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxId=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        InputDoctorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        AddNewDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        AddNewDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidateDoctorData();

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {

            ImageUri = data.getData();
            InputDoctorImage.setImageURI(ImageUri);
        }


    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);


    }

    private void ValidateDoctorData() {

        Name = InputName.getText().toString();
        Phone = InputPhone.getText().toString();
        Working = InputDescription.getText().toString();
        Location = InputLocation.getText().toString();
        Locationlatitude = InputLocationlatitude.getText().toString();
        Locationlongtitude = InputLocationlongtitude.getText().toString();
        Specialist = InputServicetype.getText().toString();
        Servicetime = InputServicetype.getText().toString();
        Rating = InputRating.getText().toString();


        AddNewDoctorButton.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);

        if (ImageUri == null) {

            AddNewDoctorButton.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);

            Toast.makeText(this, "Doctor image is mandatory !", Toast.LENGTH_SHORT).show();


        } else if (TextUtils.isEmpty(Name)) {

            Toast.makeText(this, "Please write Doctor name !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Phone)) {

            Toast.makeText(this, "Please write Doctor Phone number !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Working)) {

            Toast.makeText(this, "Please write Doctor description !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Location)) {

            Toast.makeText(this, "Please write Doctor location !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlatitude)) {

            Toast.makeText(this, "Please write  Doctor Location latitude !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Locationlongtitude)) {

            Toast.makeText(this, "Please  place Doctor Location longtitude !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Servicetime)) {

            Toast.makeText(this, "Please write Service time !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else if (TextUtils.isEmpty(Specialist)) {

            Toast.makeText(this, "Please write Service type !", Toast.LENGTH_SHORT).show();
            AddNewDoctorButton.setVisibility(View.VISIBLE);

        } else {



            StoreDoctorInformation();
        }

    }

    private void StoreDoctorInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DoctorRandomKey = saveCurrentDate + " " + saveCurrentTime;

        final StorageReference filePath = DoctorImagesRef.child(ImageUri.getLastPathSegment() + DoctorRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AddNewDoctorsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                AddNewDoctorButton.setVisibility(View.VISIBLE);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AddNewDoctorsActivity.this, "Doctor Image uploaded Successfully", Toast.LENGTH_SHORT).show();

                AddNewDoctorButton.setVisibility(View.VISIBLE);

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

                            Toast.makeText(AddNewDoctorsActivity.this, "got the Doctor image Url Successfully", Toast.LENGTH_SHORT).show();
                            AddNewDoctorButton.setVisibility(View.VISIBLE);

                            SaveDoctorInfoToDatabase();
                        }
                    }
                });
            }
        });


    }

    private void SaveDoctorInfoToDatabase() {
        HashMap<String, Object> DoctorMap = new HashMap<>();
        //   DoctorMap.put("pid", DoctorRandomKey);
        DoctorMap.put("name", Name);
        DoctorMap.put("id", String.valueOf(maxId+1));
        DoctorMap.put("category", CategoryName);
        DoctorMap.put("phone", Phone);
        DoctorMap.put("working", Working);
        DoctorMap.put("location", Location);
        DoctorMap.put("locationlatitude", Locationlatitude);
        DoctorMap.put("locationlongtitude", Locationlongtitude);
        DoctorMap.put("specialist", Specialist);
        DoctorMap.put("servicetime", Servicetime);
        DoctorMap.put("rating", Rating);
        DoctorMap.put("url", downloadImageUrl);

        DoctorRef.child(String.valueOf(maxId+1)).setValue(DoctorMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                         /*   Intent intent = new Intent(AddNewDoctorsActivity.this, AdminCategoryActivity.class);
                            startActivity(intent);  */
                            Toast.makeText(AddNewDoctorsActivity.this, "Doctor is added successfully", Toast.LENGTH_SHORT).show();
                            AddNewDoctorButton.setVisibility(View.VISIBLE);

                        } else {

                            String message = task.getException().toString();
                            Toast.makeText(AddNewDoctorsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            AddNewDoctorButton.setVisibility(View.VISIBLE);


                        }
                    }


                });

    }


}
