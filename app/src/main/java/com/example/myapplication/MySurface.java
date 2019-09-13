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

        surfaceHolder = getHolder();

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setAntiAlias(true);


        // this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // paint.setStyle(Style.FILL);
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        drawGraph(canvas);

    }

    private void drawGraph(Canvas canvas) {
//        surfaceHolder = getHolder();
//        Canvas canvas = surfaceHolder.lockCanvas();
        float[] yArr = {600, 500, 800, 200, 400, 450};

        float startX = 0;
        float startY = 600;
        float stopX = startX + 100;
        float stopY = 500;
        canvas.drawLine(startX, startY, stopX, stopY, paint);

        startX = stopX;
        startY = 500;
        stopX = startX + 100;
        stopY = 800;

        canvas.drawLine(startX, startY, stopX, stopY, paint);
//        for (int i = 0; i < yArr.length; ++i) {
//            startY = yArr[i];
//            stopY = yArr[i+1];
//            stopX = startX+100;
//            canvas.drawLine(startX, startY, stopX, stopY, paint);
//            startX = stopX;
//
//        }
//        surfaceHolder.unlockCanvasAndPost(canvas);



    }
}
