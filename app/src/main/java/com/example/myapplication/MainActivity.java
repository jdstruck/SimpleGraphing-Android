package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    MySurface customSurfaceView;

    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        customSurfaceView = new MySurface(getApplicationContext());

        customSurfaceView.setBackgroundColor(Color.WHITE);
//        customSurface
        customSurfaceView.setOnTouchListener(this);
        setContentView(customSurfaceView);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        float x = motionEvent.getX();
        float y = motionEvent.getY();

        customSurfaceView.drawText();
        


        return true;
    }
}
