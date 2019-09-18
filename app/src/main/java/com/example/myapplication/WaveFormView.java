package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;

public class WaveFormView extends View {

    private static final float defaultFrequency          = 1.5f;
    private static final float defaultAmplitude          = 1.0f;
    private static final float defaultIdleAmplitude      = 0.01f;
    private static final float defaultNumberOfWaves      = 5.0f;
    private static final float defaultPhaseShift         = -0.15f;
    private static final float defaultDensity            = 5.0f;
    private static final float defaultPrimaryLineWidth   = 3.0f;
    private static final float defaultSecondaryLineWidth = 1.0f;

    private float phase;
    private float amplitude;
    private float frequency;
    private float idleAmplitude;
    private float numberOfWaves;
    private float phaseShift;
    private float density;
    private float primaryWaveLineWidth;
    private float secondaryWaveLineWidth;
    Paint mPaintColor;
    Rect rect;
    boolean isStraightLine = false;
    private byte[] waveform;

    public WaveFormView(Context context) {
        super(context);
        setUp();
    }

    public WaveFormView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public WaveFormView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    public WaveFormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp();
    }

    private void setUp() {
        this.frequency = defaultFrequency;

        this.amplitude = defaultAmplitude;
        this.idleAmplitude = defaultIdleAmplitude;

        this.numberOfWaves = defaultNumberOfWaves;
        this.phaseShift = defaultPhaseShift;
        this.density = defaultDensity;

        this.primaryWaveLineWidth = defaultPrimaryLineWidth;
        this.secondaryWaveLineWidth = defaultSecondaryLineWidth;
        mPaintColor = new Paint();
        mPaintColor.setColor(Color.WHITE);
    }

    public void updateAmplitude(float ampli, boolean isSpeaking) {
        this.amplitude = Math.max(ampli, idleAmplitude);
        isStraightLine = isSpeaking;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        rect = new Rect(0,0, getWidth(), getWidth());
        canvas.drawColor(Color.BLUE);
        /*canvas.drawRect(rect, mPaintColor);*/
        if(isStraightLine) {
            for (int i = 0; i < numberOfWaves; i++) {
                mPaintColor.setStrokeWidth(i == 0 ? primaryWaveLineWidth : secondaryWaveLineWidth);
                float halfHeight = getHeight() / 2;
                float width = getWidth();
                float mid = getWidth() / 2;

                float maxAmplitude = halfHeight - 4.0f;
                float progress = 1.0f - (float) i / this.numberOfWaves;
                float normedAmplitude = (1.5f * progress - 0.5f) * this.amplitude;
                Path path = new Path();

                float multiplier = Math.min(1.0f, (progress / 3.0f * 2.0f) + (1.0f / 3.0f));

                for (float x = 0; x < width + density; x += density) {
                    // We use a parable to scale the sinus wave, that has its peak in the middle of the view.
                    float scaling = (float) (-Math.pow(1 / mid * (x - mid), 2) + 1);

                    float y = (float) (scaling * maxAmplitude * normedAmplitude * Math.sin(2 * Math.PI * (x / width) * frequency + phase) + halfHeight);

                    if (x == 0) {
                        path.moveTo(x, y);
                    } else {
                        path.lineTo(x, y);
                    }
                }
                mPaintColor.setStyle(Paint.Style.STROKE);
                mPaintColor.setAntiAlias(true);
                canvas.drawPath(path, mPaintColor);

            }
        } else {
            canvas.drawLine(5,getHeight()/2, getWidth(), getHeight()/2,mPaintColor );
            canvas.drawLine(0, getHeight()/2, getWidth(),getHeight()/2,mPaintColor);
            canvas.drawLine(-5,getHeight() / 2, getWidth(), getHeight()/2,mPaintColor );
        }
        this.phase += phaseShift;
        invalidate();
    }
        public void setWaveform(byte[] waveform) {
        this.waveform = Arrays.copyOf(waveform, waveform.length);
        invalidate();
    }
}

//public class WaveformView extends View {
//    private byte[] waveform;
//
//    private WaveformRenderer renderer;
//
//    public WaveformView(Context context) {
//        super(context);
//    }
//
//    public WaveformView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public WaveformView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public void setRenderer(WaveformRenderer renderer) {
//        this.renderer = renderer;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (renderer != null) {
//            renderer.render(canvas, waveform);
//        }
//    }
//
//    public void setWaveform(byte[] waveform) {
//        this.waveform = Arrays.copyOf(waveform, waveform.length);
//        invalidate();
//    }
//}