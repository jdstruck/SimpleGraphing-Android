package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    private float circleX = 0;
    private float circleY = 0;

    public MySurface(Context context) {
        super(context);

        init();

    }

    private void init() {
        surfaceHolder = getHolder();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {}

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        drawGraph(canvas);

    }

    private void drawGraph(Canvas canvas) {

        float[] yArr = {600, 500, 800, 200, 400, 450};

        float startX = 0;
        float startY = 500;
        float stopX = startX;
        float stopY = startY; // = yArr[i+1];


        for (int i=0; i < 100; ++i) {
            startX = stopX;
            stopX = startX + 10;
            startY = stopY;
            stopY = startY + i;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            System.out.println("startX: " + startX + " stopX: " + stopX + " startY: " + startY + " stopY: " + stopY);
        }




    }

    public void drawText() {
        System.out.println("drawText");
    }
}
