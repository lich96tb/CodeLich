package com.audiovideoplayer.canvastonghop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private Paint mPaint;
    private TextPaint mTextPaint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //set mau
        mPaint.setColor(Color.BLUE);
        //set do mờ<255&&>0
        // mPaint.setAlpha(255);
        //do rộng của nét vẽ
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        // mPaint.setStyle(Paint.Style.FILL);

        //text
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#16a085"));
        mTextPaint.setTextSize(50);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        drawText() để vẽ một text.
        //
        //        drawRect() để vẽ hình vuông, hình chữ nhật.
        //
        //        drawOval() để vẽ hình oval.
        //
        //        drawArc() để vẽ hình quạt.
        //
        //        drawBitmap() để vẽ ảnh bitmap.


        //ve listPoint
        float array[] = {20, 140, 240, 340, 440};
        for (float k : array) {
            canvas.drawPoint(getWidth() / 2, getHeight() / 2 - k, mPaint);
        }

        //ve doan thoang
        //canvas.drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
        canvas.drawLine(0, 0, getWidth() / 2, getHeight() / 2, mPaint);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth(), 0, mPaint);

        //ve hinh chu nhat
        // canvas.drawRect(float left, float top, float right, float bottom, @NonNull Paint paint)
        canvas.drawRect(getWidth() / 2 - 150, getHeight() / 2, getWidth() / 2 + 150, getHeight() / 2 + 500, mPaint);

        //ve duong tron
//        canvas.drawCircle(float cx, float cy, float radius, @NonNull Paint paint)
        float radius = 200;
        canvas.drawCircle(getWidth() / 2, getHeight() / 2 - (440 + radius), radius, mPaint);

        //ve oval
        //   canvas.drawOval(float left, float top, float right, float bottom, @NonNull Paint paint)
        canvas.drawOval(0, getHeight() / 2 + 500, getWidth(), getHeight() / 2 + 500 + 150 + 250, mPaint);

        //ve hinh quat
        //  canvas.drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint);
        //Draw Arc
        float width = 400;
        float height = 400;

        float left = (getWidth() - width) / 2.0f;
        float top = (getHeight() - height) / 2.0f-200;
     // canvas.drawArc(left, top, left + width, top + height, 45, 270, true, mPaint);
      canvas.drawArc(left, top, left + width, top + height, 45, 1, true, mPaint);
//        Paint paint = new Paint();
//        canvas.drawPaint(paint);
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(16);
       canvas.drawText("My Text", getWidth()/2,100, mPaint);
    }
}
