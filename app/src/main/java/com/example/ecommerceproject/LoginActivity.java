package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.ecommerceproject.services.UserService;
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
    FirebaseAuth mAuth;

   @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.emailEdit);
        passwordTxt = findViewById(R.id.passwordEdit);
        loginBtn = findViewById(R.id.loginBtn);
        //create instance
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email=String.valueOf(emailTxt.getText());
                password=String.valueOf(passwordTxt.getText());
                // Log.w("TAG", "login email and password :"+ email + "password "+password );

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                UserService.loginUser(LoginActivity.this,email,password);

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





    }