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

    static final int ANIMATION_DURATION = 800;
    static final float ALPHA_VALUE = 1f;
    static final float TRANSLATION_Y_VALUE = 0f;
    private String CHECK_FIRST_RUN;
    private String SHARED_PREFS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CHECK_FIRST_RUN = getString(R.string.first_run);
        SHARED_PREFS = getString(R.string.shared_prefs);

        com.calculator.omnical.databinding.ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvAppName.animate().alpha(ALPHA_VALUE).translationY(TRANSLATION_Y_VALUE).setDuration(ANIMATION_DURATION);

        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        boolean firstRun = sharedPrefs.getBoolean(CHECK_FIRST_RUN, false);
        if (!firstRun) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(CHECK_FIRST_RUN, true);
            editor.apply();

            final int SPLASH_SCREEN_DURATION = 1000;
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }, SPLASH_SCREEN_DURATION);
        } else {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}