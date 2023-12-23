package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText=findViewById(R.id.usernameeditview);
        emailEditText=findViewById(R.id.emaileditview);
        passwordEditText=findViewById(R.id.passwordeditview);
        buttonReg=findViewById(R.id.registerBtn);
        //create instance
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("users");

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,name;
                email=emailEditText.getText().toString();
                password=passwordEditText.getText().toString();
                name=usernameEditText.getText().toString();
                if (name.isEmpty()){
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


                // add to database
                addUserToDb(email,password,name);

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

    private void addUserToDb(String email,String password,String name){
        // create a hashmap
        HashMap<String,Object> usersHashmap=new HashMap<>();
        usersHashmap.put("username",name);
        usersHashmap.put("email",email);
        usersHashmap.put("password",password);

        String key= reference.push().getKey();
        usersHashmap.put("key",key);
        reference.child(key).setValue(usersHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterActivity.this, "Account created.",
                        Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}