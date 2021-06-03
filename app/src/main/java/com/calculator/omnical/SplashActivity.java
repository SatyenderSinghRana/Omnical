package com.calculator.omnical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
/*
    Splash Screen implementation
    Needs to be changed/modified later
*/

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPrefs = getSharedPreferences("SHARED PREFS", Context.MODE_PRIVATE);
        boolean firstRun = sharedPrefs.getBoolean("firstRun", false);
        if (!firstRun) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean("firstRun", true);
            editor.apply();

            int splashScreenDuration = 1000;
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
            }, splashScreenDuration);
        } else {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}