package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    MySurface customSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customSurfaceView = new MySurface(getApplicationContext());

        customSurfaceView.setBackgroundColor(Color.WHITE);
        customSurfaceView.setOnTouchListener(this);
        setContentView(customSurfaceView);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return true;
    }
}
