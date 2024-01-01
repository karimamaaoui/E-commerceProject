package com.example.ecommerceproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.ecommerceproject.models.Product;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.UUID;

public class ProductActivity extends AppCompatActivity {
    private EditText priceText,nameText,descriptionText,categoryText;
    ImageView imageView,arrowprod;

    private Button savebtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private static final int GALLERY_REQUEST_CODE = 123;
    StorageReference storageReference;
    Uri image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        // Initialize Firebase components
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("products");

        // Initialize UI elements
        priceText=findViewById(R.id.priceprod);
        descriptionText=findViewById(R.id.desprod);
        imageView=findViewById(R.id.imageProd);
        categoryText = findViewById(R.id.categoryprod);
        nameText=findViewById(R.id.nameprod);
        arrowprod=findViewById(R.id.arrowprod);
        savebtn=findViewById(R.id.savebtn);



        //set up a click listener go back button
        arrowprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); }
        });



        // Initialize Firebase Storage
        storageReference = FirebaseStorage.getInstance().getReference();

        // Set an OnClickListener for the ImageView to open the gallery
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        // Set an OnClickListener for the "Save" button
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Retrieve text values from EditText fields
                String prodName = nameText.getText().toString().trim();
                String prodDescription = descriptionText.getText().toString().trim();
                String prodPrice = priceText.getText().toString().trim();
                String prodCategory = categoryText.getText().toString().trim();

                // Check if the necessary fields are not empty and an image is selected

                if (!TextUtils.isEmpty(prodName)  && !TextUtils.isEmpty(prodPrice) &&
                        !TextUtils.isEmpty(prodDescription) && !TextUtils.isEmpty(prodCategory)
                        && !TextUtils.isEmpty(prodPrice) &&  image != null) {
                    // If conditions are met, upload the image and data
                    uploadImage( prodName,prodPrice,prodDescription,prodCategory,image);
                } else {
                    // Display message if required fields are empty
                    Toast.makeText(ProductActivity.this, "Please enter prod name and select an image", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the gallery picker and is successful
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Assign the selected image URI to the 'image' variable
            image = selectedImageUri;

            // Perform actions with the selected image URI (e.g., display in ImageView)
            imageView.setImageURI(selectedImageUri);

            // Inform the user that the image has been selected
            Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
        }
    }



    // Method to open the gallery
    private void openGallery() {
        // Create an intent to open the gallery picker
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void uploadImage( String name,String price,String description,String category,Uri file) {
        // Create reference for the image in Firebase Storage
        StorageReference ref = storageReference.child("product_images/" + UUID.randomUUID().toString());

        // Upload the image to Firebase Storage
        ref.putFile(file)
                .addOnSuccessListener(taskSnapshot -> {
                    // If the upload is successful, get the download URL of the image
                    ref.getDownloadUrl().addOnSuccessListener(uri -> {
                        // Insert data (product information) into Firebase Realtime Database
                        insertData(name,price,description,category, uri.toString());
                    });
                })
                .addOnFailureListener(e -> {
                    // If the upload fails, display an error message
                    Toast.makeText(ProductActivity.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void insertData(String name,String price,String description ,String category , String imageUrl) {
        // Generate a unique product ID
        String prodId = reference.push().getKey();
        // Create a Product object
        Product product = new Product( prodId,name,price,description, category,imageUrl);
        // Insert the product data into Firebase Realtime Database
        reference.child(prodId).setValue(product)
                .addOnSuccessListener(aVoid -> {
                    // If the insertion is successful, display a success message
                    Toast.makeText(ProductActivity.this, "Product uploaded successfully", Toast.LENGTH_SHORT).show();
                    // Clear the form
                    nameText.setText("");
                    priceText.setText("");
                    categoryText.setText("");
                    descriptionText.setText("");
                    priceText.setText("");
                })
                .addOnFailureListener(e -> {
                    // If the insertion fails, display an error message
                    Toast.makeText(ProductActivity.this, "Failed to upload Product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}