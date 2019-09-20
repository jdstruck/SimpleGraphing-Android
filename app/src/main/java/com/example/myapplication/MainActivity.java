package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    MySurface customSurfaceView;
    private LinearLayout parentLinearLayout;
    boolean isPlaying = false;
    private Visualizer visualizer;
    private WaveFormView waveFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.parentLinearLayout = (LinearLayout) findViewById(R.id.parentLinearLayout);

        customSurfaceView = new MySurface(getApplicationContext());
        //customSurfaceView.setBackgroundColor(Color.WHITE);

        //addSeekBar();
        parentLinearLayout.addView(customSurfaceView);
        customSurfaceView.setOnTouchListener(this);
        customSurfaceView.setWillNotDraw(false);
    }

    private void addSeekBar() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View seekBarView = inflater.inflate(R.layout.seekbar, null);
        parentLinearLayout.addView(seekBarView, parentLinearLayout.getChildCount() - 1);
    }
    private void addCoordTextView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View seekBarView = inflater.inflate(R.layout.coord_text_view, null);
        parentLinearLayout.addView(seekBarView, parentLinearLayout.getChildCount() - 1);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(view instanceof SurfaceView) {
            view.invalidate();

            float x = motionEvent.getX();

            float y = motionEvent.getY();

            customSurfaceView.xCoord = x;
            customSurfaceView.yCoord = y;

            customSurfaceView.drawGraph();

            return true;
        }else {
            return false;
        }
    }
}
