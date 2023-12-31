package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView  profileEmail,profileUsername,profilePassword;
    TextView titleUsername;
     MaterialToolbar toolbar;
     DrawerLayout drawerLayout;
     NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileEmail=findViewById(R.id.profileEmail);
        profileUsername=findViewById(R.id.profileUsername);
        profilePassword=findViewById(R.id.profilePassword);
        titleUsername=findViewById(R.id.titleUsername);

        toolbar = findViewById(R.id.topAppbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);

        // Set up the navigation drawer
        DrawerUtil.setupDrawer(this, toolbar, drawerLayout, navigationView);




        // Retrieve user email from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("useremail", "");
         Log.w("TAG", "userEmail from 38 profile:"+ userEmail  );

        // Use the email to fetch additional user data from the database
        fetchUserDataFromDatabase(userEmail);

    }

    private void fetchUserDataFromDatabase(String userEmail) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");

        userRef.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    // Retrieve user data from the snapshot
                    String username = userSnapshot.child("username").getValue(String.class);
                    String password = userSnapshot.child("password").getValue(String.class);

                    // Update UI with user data
                    profileEmail.setText(userEmail);
                    profileUsername.setText(username);
                    titleUsername.setText(username);
                    profilePassword.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}