package com.calculator.omnical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initiateSplash();
    }

    private void initiateSplash() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i=new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 1000);
    }
}
