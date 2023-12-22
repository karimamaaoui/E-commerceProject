package com.example.ecommerceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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