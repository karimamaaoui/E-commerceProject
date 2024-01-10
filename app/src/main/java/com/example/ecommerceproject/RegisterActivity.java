package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.Tag;
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

import com.example.ecommerceproject.services.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText emailEditText,passwordEditText,usernameEditText;
    Button buttonReg;

    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailEditText=findViewById(R.id.emaileditview);
        passwordEditText=findViewById(R.id.passwordeditview);
        usernameEditText=findViewById(R.id.usernameeditview);

        buttonReg=findViewById(R.id.registerBtn);

        //create instance
        mAuth = FirebaseAuth.getInstance();

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,username;
                email=emailEditText.getText().toString();
                username=usernameEditText.getText().toString();
                password=passwordEditText.getText().toString();
                if (username.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Enter username",Toast.LENGTH_SHORT).show();
                    return;
                }



                if (email.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }


                // Call the addUserToDb method from UserService
                UserService.addUserToDb(RegisterActivity.this, email, password,username);
                //retrieve the user's email from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
                String userEmail = sharedPreferences.getString("useremail", "");
                Log.w("TAG", "user FROM REGISTER LINE 110  " + userEmail.isEmpty());
                //If the user's email is empty, you navigate to the LoginActivity
                if(userEmail.isEmpty()){
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });



        TextView alreadyhaveaccountText=findViewById(R.id.alreadyhaveaccountText);

        // Create a SpannableString from the text of alreadyhaveaccountText
        SpannableString spannableString=new SpannableString(alreadyhaveaccountText.getText());
        // Create a ForegroundColorSpan to set the text color to blue
        ForegroundColorSpan fcBlue=new ForegroundColorSpan(Color.BLUE);
        // Apply the ForegroundColorSpan to the spanString from character index 31 to the end of the text
        spannableString.setSpan(fcBlue,31,alreadyhaveaccountText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the modified SpannableString back to the donhaveaccountText TextView
        alreadyhaveaccountText.setText(spannableString);
        // Set an OnClickListener on alreadyhaveaccountText

        alreadyhaveaccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate from RegisterActivity to LoginActivity
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                //start the LoginActivity
                startActivity(intent);
            }
        });




    }

}