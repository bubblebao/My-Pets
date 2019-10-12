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
import com.project.mypetsphuket.AdminCategoryActivity;
import com.project.mypetsphuket.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private TextView closeTextBtn , NextTextButton;
    private String CategoryName, Description, Name, Location , Locationlatitude ,Locationlongtitude , Price , saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription ,InputLocation ,InputLocationlatitude ,InputLocationlongtitude, InputProductPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    long maxId=0;

    private ProgressBar loadingProgress;



    private Button LogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);



       // Toast.makeText(AdminAddNewProductActivity.this, "This is  " + CategoryName + " ready to add now ", Toast.LENGTH_SHORT).show();
      //  Toast.makeText(AdminAddNewProductActivity.this, "+ +", Toast.LENGTH_SHORT).show();
        closeTextBtn = (TextView) findViewById(R.id.close_admin_btn);
        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        InputProductImage = (ImageView) findViewById(R.id.select_product_image);
        InputProductName = (EditText) findViewById(R.id.product_name);
        InputProductDescription = (EditText) findViewById(R.id.product_description);
        InputLocation = (EditText) findViewById(R.id.product_location);
        InputProductPrice = (EditText) findViewById(R.id.product_price);

        AddNewProductButton = (Button) findViewById(R.id.add_new_product);


        loadingProgress = findViewById(R.id.Add_Product_ProgressBar);

        ProductsRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            maxId=(dataSnapshot.getChildrenCount());

                    }

                          @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
                 });


                InputProductImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        OpenGallery();

                    }
                });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateProductData();
            }
        });



        // userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }


    private void ValidateProductData() {


        Name = InputProductName.getText().toString();
        Description = InputProductDescription.getText().toString();
        Location = InputLocation.getText().toString();
        Price = InputProductPrice.getText().toString();

        if (ImageUri == null) {

            Toast.makeText(this, "Product image is mandatory !", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(Description)) {

            Toast.makeText(this, "Please write product description !", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(Location)) {

            Toast.makeText(this, "Please write place location !", Toast.LENGTH_SHORT).show();

         }else if (TextUtils.isEmpty(Price)) {

            Toast.makeText(this, "Please write Price !", Toast.LENGTH_SHORT).show();

        }
        else
        {

            StoreProductInformation();
        }


    }

    private void StoreProductInformation() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        AddNewProductButton.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AdminAddNewProductActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                AddNewProductButton.setVisibility(View.VISIBLE);

            }

        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AdminAddNewProductActivity.this, "Product Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

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

                            Toast.makeText(AdminAddNewProductActivity.this, "got the Product image Url Successfully", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                    }
                }
            });
           }
        });
    }

    private void SaveProductInfoToDatabase() {

        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid", productRandomKey);
        productMap.put("image", downloadImageUrl);
        productMap.put("category", CategoryName);
        productMap.put("pname",Name);
        productMap.put("description", Description);
        productMap.put("location", Location);
        productMap.put("price", Price);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);

        //Location , Locationlatitude ,Locationlongtitude , Servicetype
      //  productMap.put("date", saveCurrentDate);
      //  productMap.put("time", saveCurrentTime);
        ProductsRef.child(String.valueOf(maxId+1)).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    AddNewProductButton.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(AdminAddNewProductActivity.this, AdminCategoryActivity.class);
                    startActivity(intent);
                    Toast.makeText(AdminAddNewProductActivity.this, "Product is added successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String message = task.getException().toString();
                    Toast.makeText(AdminAddNewProductActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    AddNewProductButton.setVisibility(View.VISIBLE);

            }
        }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }

    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);

    }
}
