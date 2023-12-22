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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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