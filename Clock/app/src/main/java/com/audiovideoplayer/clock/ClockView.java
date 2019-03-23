package com.audiovideoplayer.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {
    private int height, width;
    private int padding = 0;
    private int fonSize = 0;
    private int numeralSpacing = 0;
    private int handTruncation, hourHandTruncation = 0;
    private int radius = 0;
    private Paint paint;
    private Boolean isInit = false;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Rect rect = new Rect();

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    private void initClock() {
        height = getHeight();
        width = getWidth();
        padding = numeralSpacing + 50;
        fonSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        paint = new Paint();
        isInit = true;

    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) {
            initClock();
        }
        //  canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        //dau cham tron o trung tam dong ho
        drawCenter(canvas);
        //hien thi do tren man hinh
        drawNumeral(canvas);
        //hien thi kim ddong ho
        drawHands(canvas);
        //cap nhat thoi gian
        postInvalidateDelayed(500);
        //  invalidate();
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRaudius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2, height / 2, (float) (width / 2 + Math.cos(angle) * handRaudius), (float) (height / 2 + Math.sin(angle) * handRaudius), paint);
    }

    private void drawHands(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + calendar.get(Calendar.MINUTE) * 60) * 5f, true);
        drawHand(canvas, calendar.get(Calendar.MINUTE), false);
        drawHand(canvas, calendar.get(Calendar.SECOND), false);
    }

    private void drawNumeral(Canvas canvas) {
        paint.setTextSize(fonSize);
        for (int number : numbers) {
            String tmp = String.valueOf(number);
          paint.getTextBounds(tmp, 0, tmp.length(), rect);
            double angle = - Math.PI / 6 * (number - 3);
            int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
            Log.e("ADSF "," x:"+x+"   y:"+y);
            canvas.drawText(tmp, x, y, paint);
            x= (int) (radius*Math.cos(angle));
            y= (int) (radius*Math.sin(angle));
            canvas.drawPoint( x,y,paint);

        }
    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, 12, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height / 2, radius + padding - 10, paint);

    }
}
