package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText emailTxt,passwordTxt;
    Button loginBtn;
  //  FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

  /*  @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            finish();

        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.emailEdit);
        passwordTxt = findViewById(R.id.passwordEdit);
        loginBtn = findViewById(R.id.loginBtn);
       // mAuth = FirebaseAuth.getInstance();
        //create instance
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("users");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email=String.valueOf(emailTxt.getText());
                password=String.valueOf(passwordTxt.getText());
                Log.w("TAG", "login email and password :"+ email + "password "+password );

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                /*
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LoginActivity.this, "login succesful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "login failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                */
                loginUser(email,password);


            }
        });

        TextView donhaveaccountText=findViewById(R.id.donhaveaccountText);
        // Create a SpannableString from the text of donhaveaccountText
        SpannableString spanString = new SpannableString(donhaveaccountText.getText());
        // Create a ForegroundColorSpan to set the text color to blue
        ForegroundColorSpan fcBlue=new ForegroundColorSpan(Color.BLUE);
        // Apply the ForegroundColorSpan to the spanString from character index 31 to the end of the text
        spanString.setSpan(fcBlue,31,donhaveaccountText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the modified SpannableString back to the donhaveaccountText TextView
        donhaveaccountText.setText(spanString);
        // Set an OnClickListener on donhaveaccountText
        donhaveaccountText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate from LoginActivity to RegisterActivity
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                //start the registerActivity
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password) {
        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User found in the database
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String storedPassword = userSnapshot.child("password").getValue(String.class);
                        if (storedPassword != null && storedPassword.equals(password)) {
                            // Password matches, authentication successful
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Password is wrong
                            Toast.makeText(LoginActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // User not found in the database
                    Toast.makeText(LoginActivity.this," User not found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error, if necessary
            }
        });
    }





    }