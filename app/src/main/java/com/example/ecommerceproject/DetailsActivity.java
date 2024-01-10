package com.example.ecommerceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    TextView detailName,detailPrice,detaildescription,detailcategory;
    ImageView imageproddetails,imageViewArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Initializing views

        detailName=findViewById(R.id.detailName);
        detailPrice=findViewById(R.id.detailPrice);
        detailcategory=findViewById(R.id.detailcategory);
        detaildescription=findViewById(R.id.detaildescription);
        imageproddetails=findViewById(R.id.imageproddetails);
        // Adding a click listener to the arrow image to go back

        imageViewArrow = findViewById(R.id.imageViewArrow);
        imageViewArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); }
        });
        // Retrieving data passed through Intent extras
        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            detailName.setText(bundle.getString("name"));
            detailPrice.setText(bundle.getString("price"));
            detailcategory.setText(bundle.getString("category"));
            detaildescription.setText(bundle.getString("description"));
            // Loading and displaying the product image using Glide
            String imageUrl = bundle.getString("imageUrl");
            Glide.with(this)
                    .load(imageUrl)
                    .error(R.drawable.product_bg) // Placeholder for error case
                    .into(imageproddetails);

        }
    }
}