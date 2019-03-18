package com.example.solar.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GraphicView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap mBitmap;
    float width = 100;
    float stopX;
    float stopY;
    public GraphicView(Context context) {
        super(context);
    }

    public GraphicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public void setValue(int k) {
        if (k==1){
            stopY=stopY+100;
        }

     mPaint.setColor(Color.RED);
//        width = 500;
//        mPaint.setAlpha(255);
//        mPaint.setStrokeWidth(50);
//        //to het
//        //  mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//
//        //to het trong hinh
//        //   mPaint.setStyle(Paint.Style.FILL);
//
//        //chi to nhung cho nao duoc ve
//        mPaint.setStyle(Paint.Style.STROKE);


        stopY = 0;
        stopX = getWidth() / 2;
        new Thread() {
            public void run() {
              while (true){


                    try {
                        if (stopY<getWidth()&&stopX<getWidth()){
                            stopY=stopY+10;
                            stopX=stopX+10;
                        }else if (stopY>=getHeight()/2){
                            stopY=stopY+10;
                            stopX=stopX-10;
                        }

                       invalidate();
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //Draw Point
//        int x = 200;
//        int y = 200;
//        canvas.drawPoint(x, y, mPaint);

////Draw Line
//        for(int i=0;i<10;i++){
        float startX = getWidth()/2;
        float startY = getHeight()/2;

        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    //}

//Draw Rect
//         width = 500;
//        float height = 200;
//
//        float left = (getWidth() - width) / 2.0f;
//        float top = (getHeight() - height) / 2.0f;
//        canvas.drawRect(left, top, left + width, top + height, mPaint);


//        //Draw Circle
//     float radius = 200.0f;
//        float cx = getWidth()/2;
//        float cy = getHeight()/2;
//        canvas.drawCircle(cx,cy,radius,mPaint);


////Draw Oval
//        float width = 300;
//        float height = 200;
//        float left = (getWidth() - width) / 2.0f;
//        float top = (getHeight() - height) / 2.0f;
//        canvas.drawOval(new RectF(left, top, left + width, top + height), mPaint);


//        //Draw Arc
//        float width = 400;
//        float height = 400;
//
//        float left = (getWidth() - width) / 2.0f;
//        float top = (getHeight() - height) / 2.0f;
//        canvas.drawArc(new RectF(left, top, left + width, top + height),0, 300, true, mPaint);


//        //Draw Bitmap
//        float left = (getWidth() - mBitmap.getWidth()) / 2.0f;
//        float top = (getHeight() - mBitmap.getHeight())/2;
//        canvas.drawBitmap(mBitmap, left, top, mPaint);

        //cat 1/4
        //Draw Bitmap
//        Rect src = new Rect(0, 0, mBitmap.getWidth() / 2, mBitmap.getHeight() / 1);
//        int left = (getWidth() - src.width())/2;
//        int top = (getHeight() - src.height())/2;
//
//        Rect des = new Rect(left, top, left + src.width(), top + src.height());
//        canvas.drawBitmap(mBitmap, src, des, mPaint);


    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStrokeWidth(30);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.YELLOW);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d);

    }
}
