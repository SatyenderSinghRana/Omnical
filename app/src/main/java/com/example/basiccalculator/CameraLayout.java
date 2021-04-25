package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.Preview;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;

public class CameraLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_layout);

        Preview preview = new Preview.Builder().build();
        PreviewView viewFinder = findViewById(R.id.viewFinder);


    }
}