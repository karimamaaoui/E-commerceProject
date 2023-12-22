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
    Animation imanim,teanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=findViewById(R.id.imageViewSplash);
        textView=findViewById(R.id.textNameApp);
        constraintLayout=findViewById(R.id.constraintLayoutSplash);

        imanim= AnimationUtils.loadAnimation(this,R.anim.image_animation);
        teanim= AnimationUtils.loadAnimation(this,R.anim.textanim);

        imageView.setAnimation(imanim);
        constraintLayout.setAnimation(imanim);
        textView.setAnimation(teanim);


        final Handler myhHandler=new Handler();
        myhHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                 startActivity(new Intent(SplashActivity.this,MainActivity.class));
                 finish();
            }
        },2000);
    }
}