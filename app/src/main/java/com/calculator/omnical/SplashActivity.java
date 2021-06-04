package com.calculator.omnical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.calculator.omnical.databinding.ActivitySplashBinding;

/**
 * Splash Screen implementation
 * Needs to be changed/modified later
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.calculator.omnical.databinding.ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final int ANIMATION_DURATION = 800;
        final float ALPHA_VALUE = 1f;
        final float TRANSLATION_Y_VALUE = 0f;
        binding.tvAppName.animate().alpha(ALPHA_VALUE).translationY(TRANSLATION_Y_VALUE).setDuration(ANIMATION_DURATION);

        SharedPreferences sharedPrefs = getSharedPreferences("SHARED PREFS", Context.MODE_PRIVATE);
        String checkFirstRun = "First Run";
        boolean firstRun = sharedPrefs.getBoolean(checkFirstRun, false);
        if (!firstRun) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(checkFirstRun, true);
            editor.apply();

            final int splashScreenDuration = 1000;
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }, splashScreenDuration);
        } else {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}