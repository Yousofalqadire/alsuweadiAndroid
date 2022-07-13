package com.example.alsuweadiwears2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {
    private static boolean SplashLoaded = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if(!SplashLoaded){
           setContentView(R.layout.splash_activity);
           final LinearLayout layout = findViewById(R.id.splash_layout);
           layout.setBackgroundResource(R.drawable.animated_background);
           layout.post(new Runnable() {
               @Override
               public void run() {
                  AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
                  animationDrawable.setEnterFadeDuration(1000);
                  animationDrawable.setExitFadeDuration(1000);
                  animationDrawable.start();
               }
           });
           new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              startActivity(new Intent(SplashActivity.this,MainActivity.class));
              finish();
            }
        },3000);
        SplashLoaded = true;
       }else{

           Intent intent = new Intent(SplashActivity.this,MainActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
           startActivity(intent);
           finish();
       }

    }
}
