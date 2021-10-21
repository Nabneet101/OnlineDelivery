package com.example.onlinedelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class Animation extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH =18600;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        getDelegate().getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Animation.this, MainActivity.class);
                Animation.this.startActivity(mainIntent);
                Animation.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }
