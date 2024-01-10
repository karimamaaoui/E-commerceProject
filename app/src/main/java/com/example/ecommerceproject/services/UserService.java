package com.example.ecommerceproject.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceproject.HomeActivity;
import com.example.ecommerceproject.LoginActivity;
import com.example.ecommerceproject.ProductActivity;
import com.example.ecommerceproject.ProductListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserService {
    // Method for logging in a user
    public static void loginUser(Context context, String email, String password) {
        // Firebase Authentication
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If login is successful

                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            // Check user role in the Firebase Realtime Database
                            DatabaseReference roleRef = FirebaseDatabase.getInstance().getReference("users")
                                    .child(userId)
                                    .child("role");

                            roleRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String userRole = dataSnapshot.getValue(String.class);
                                    Toast.makeText(context, "Login successful.", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(context, "Login successful.", Toast.LENGTH_SHORT).show();
                                    // Handle the role and navigate to the appropriate activity

                                    if ("admin".equals(userRole)) {
                                        // Admin user, navigate to ProductListActivity
                                        Intent intent = new Intent(context, ProductListActivity.class);
                                        context.startActivity(intent);
                                    } else if ("client".equals(userRole)) {
                                        // Client user, navigate to HomeActivity
                                        Intent intent = new Intent(context, HomeActivity.class);
                                        context.startActivity(intent);
                                    }
                                    // Finish the current activity
                                    if (context instanceof AppCompatActivity) {
                                        ((AppCompatActivity) context).finish();
                                        // Save user email in SharedPreferences

                                        saveData(context, email);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Login failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Method for saving user data in SharedPreferences
    private static void saveData(Context context, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("logindata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("logincounter", true);
        editor.putString("useremail", email);
        editor.apply();
        Log.w("TAG", "Data saved. email: from user service  " + email);

    }
    public static String getUserEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("logindata", Context.MODE_PRIVATE);
        return sharedPreferences.getString("useremail", null);
    }

    // Method for adding a new user to the Firebase Authentication and Realtime Database
    public static void addUserToDb(Context context, String email, String password, String username)
    {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If user creation is successful

                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            // Set user details in the Realtime Database
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                            ref.child(userId).child("role").setValue("client");
                            ref.child(userId).child("email").setValue(email);
                            ref.child(userId).child("username").setValue(username);

                            ref.child(userId).child("password").setValue(password);

                         //   Log.w("TAG", "EMAOL FROM REGISTER LINE 110  " + email);

                            Toast.makeText(context, "Account created.", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // logout method
    public static void logout(Context context) {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        // Finish the current activity
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).finish();
        }
        // Clear SharedPreferences

        SharedPreferences sharedPreferences= context.getSharedPreferences("logindata",Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();

    }
}
