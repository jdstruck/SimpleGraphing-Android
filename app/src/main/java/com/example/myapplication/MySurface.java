package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    private Paint gridline = null;
    public float xCoord, yCoord, frequency, amplitude;
    TextView freq_tv, amp_tv, xCoord_tv, yCoord_tv;

    public MySurface(Context context) {
        super(context);

        init();

    }

    private void init() {
        surfaceHolder = getHolder();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);

        gridline = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridline.setColor(Color.BLUE);
        gridline.setStrokeWidth(1);

        xCoord = 0;
        yCoord = 1800;


        //setBackgroundColor(Color.WHITE);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //drawGraph();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

    @Override
    public void onDraw(Canvas canvas) {

        //super.onDraw(canvas);

        drawGraph();

    }

    public float[] drawGraph() {

        surfaceHolder = getHolder();
        Canvas canvas = surfaceHolder.lockCanvas();
        int duration = this.getWidth();

        frequency = 1000 * (xCoord / this.getWidth());

        float startX = 0;
        float startY = ((canvas.getHeight()/2.0f) - (Math.abs(yCoord - canvas.getHeight()))/2.75f);
        float stopX = 0;
        float stopY = startY; // = yArr[i+1];

        amplitude = (frequency / canvas.getHeight()) * (Math.abs(yCoord - canvas.getHeight())/10);

        Paint surfaceBackground = new Paint();
        // Set the surfaceview background color.
        surfaceBackground.setColor(Color.WHITE);
        // Draw the surfaceview background color.
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        canvas.drawLine(0, getHeight()/2, getWidth(),getHeight()/2, gridline);
        double[] mSound = new double[duration];
        short[] mBuffer = new short[duration];
        for (int i = 0; i < mSound.length; i++) {
            float A = amplitude;
            double B = Math.sin(2.0*Math.PI * i/(44100/frequency));
            mSound[i] = A * Math.sin(B); //y = A sin(B(x + C)) + D
            //mBuffer[i] = (short) (mSound[i]*Short.MAX_VALUE);
            startX = stopX;
            stopX = startX + 1f;
            startY = stopY;
            stopY += mSound[i];
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            System.out.println(//"startX: " + startX + " stopX: " + stopX +
                    " startY: " + (startY-(this.getHeight()/2))+
                    " stopY: " + (stopY-(this.getHeight()/2)) +
                    " getHeight: " + getHeight() +
                    " amp: " + frequency/amplitude + " freq: " + frequency +
                    " xCoord: " + xCoord + " yCoord: " + yCoord);
        }



       // mAudioTrack.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
        //mAudioTrack.play();

        //mAudioTrack.write(mBuffer, 0, mSound.length);

//        for (int i = 0; i < 10; ++i) {
//            mAudioTrack.write(mBuffer, 0, mSound.length);
//        }
        //mAudioTrack.stop();
        //mAudioTrack.release();

        surfaceHolder.unlockCanvasAndPost(canvas);
        return new float[]{xCoord, yCoord, amplitude, frequency};
    }

    public void drawText() {
        System.out.println("drawText");
    }
}
