package com.example.ecommerceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    ConstraintLayout constraintLayout;
    // Declare Animation objects for image and text animations
    Animation imanim,teanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize ImageView, TextView, and ConstraintLayout using their corresponding IDs
        imageView=findViewById(R.id.imageViewSplash);
        textView=findViewById(R.id.textNameApp);
        constraintLayout=findViewById(R.id.constraintLayoutSplash);
        // Load animations from the XML resources
        imanim= AnimationUtils.loadAnimation(this,R.anim.image_animation);
        teanim= AnimationUtils.loadAnimation(this,R.anim.textanim);
        // Set animations for ImageView, ConstraintLayout, and TextView
        imageView.setAnimation(imanim);
        constraintLayout.setAnimation(imanim);
        textView.setAnimation(teanim);

        // Use Handler to delay the transition to the main activity by 2000 milliseconds

        final Handler myhHandler=new Handler();
        myhHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity and finish the splash activity
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                 finish();
            }
        },2000);
    }
}