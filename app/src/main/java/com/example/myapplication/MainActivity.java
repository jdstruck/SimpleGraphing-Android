package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, Visualizer.OnDataCaptureListener {
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
        // Make app full screen to hide top status bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.parentLinearLayout = (LinearLayout) findViewById(R.id.parentLinearLayout);

        waveFormView = new WaveFormView(getApplicationContext());
        waveFormView.setBackgroundColor(Color.WHITE);
        waveFormView.setOnTouchListener(this);

        customSurfaceView = new MySurface(getApplicationContext());
        //customSurfaceView.setBackgroundColor(Color.WHITE);
        customSurfaceView.setOnTouchListener(this);
        //addSeekBar();
        parentLinearLayout.addView(customSurfaceView);
        //setContentView(customSurfaceView);
        //final SeekBar seekBar = findViewById(R.id.seekBar);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                System.out.println(i);
//                customSurfaceView.frequency = i + 100;
//                customSurfaceView.drawGraph();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
    }

    private void addSeekBar() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View seekBarView = inflater.inflate(R.layout.seekbar, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(seekBarView, parentLinearLayout.getChildCount() - 1);
    }
    private void addCoordTextView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View seekBarView = inflater.inflate(R.layout.coord_text_view, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(seekBarView, parentLinearLayout.getChildCount() - 1);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // If user touch the custom SurfaceView object.
        if(view instanceof SurfaceView) {
            view.invalidate();

            float x = motionEvent.getX();

            float y = motionEvent.getY();

            customSurfaceView.frequency = x;
            customSurfaceView.amplitude = y;

            customSurfaceView.drawGraph();

            //customSurfaceView.setCircleY(y);

//            if (drawBall) {
//                // Create and set a red paint to custom surfaceview.
//                Paint paint = new Paint();
//                paint.setColor(Color.RED);
//                customSurfaceView.setPaint(paint);
//
//                customSurfaceView.drawLine();
//            } else {
//                // Create and set a green paint to custom surfaceview.
//                Paint paint = new Paint();
//                paint.setColor(Color.GREEN);
//                customSurfaceView.setPaint(paint);
//
//                customSurfaceView.drawRect();
//            }

            // Tell android os the onTouch event has been processed.
            return true;
        }else
        {
            // Tell android os the onTouch event has not been processed.
            return false;
        }
    }

    private void startVisualizer() {
        visualizer = new Visualizer(0);

    }

    @Override
    public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
        if (waveFormView != null) {
            waveFormView.setWaveform(waveform);
        }
    }

    @Override
    public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int i) {

    }


}
