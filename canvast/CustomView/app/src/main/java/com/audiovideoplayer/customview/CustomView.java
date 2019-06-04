package com.audiovideoplayer.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class CustomView extends View {
    private Rect mRectSquare;
    private Paint mPaintSquare;
    public static int SQUARE_SIZE = 200;
    private int squareColor, mSquareSize;
    private Paint mPaintCircle;
    private float mCircleX, mCircleY, mCircleRadius = 100f;
    private Bitmap bitmap;


    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(@Nullable AttributeSet set) {
        Log.e("AAAAAAAA"," "+getWidth());

        //hinh vuong
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectSquare = new Rect();
        mPaintSquare.setColor(Color.YELLOW);

        //hinh tron
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.YELLOW);

        //bit map
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nhung);
        //set lai size bitmap
     //
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int padding=150;
                bitmap = getResizedBitmap(bitmap, getWidth()-padding, getHeight()-padding);
            }
        });


        if (set == null)
            return;
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);
        //set mau mac dinh ben atrrs
        squareColor = ta.getColor(R.styleable.CustomView_square_color, Color.GREEN);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE);
//cap nhat lai mau
        mPaintSquare.setColor(squareColor);

        ta.recycle();
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int width, int height) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, width, height);
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mRectSquare.left = 100;
        mRectSquare.top = 100;
        mRectSquare.right = mRectSquare.left + mSquareSize;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;
        canvas.drawRect(mRectSquare, mPaintSquare);


        //ve hinh trong
//        float cx,cy,radius=100f;
//        cx=getWidth()-radius-50f;
//        cy=mRectSquare.top+ (mRectSquare.height()/2);
//        canvas.drawCircle(cx,cy,radius,mPaintCircle);

        //đẻ di chuyen vong trong
        if (mCircleX == 0f || mCircleY == 0f) {
            mCircleX = getWidth() / 2;
            mCircleY = getHeight() / 2;
        }
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);

        //canvas hinh anh
        float imagX=(getWidth()-bitmap.getWidth())/2;
        float imagY=(getHeight()-bitmap.getHeight())/2;
      //  canvas.drawBitmap(bitmap, imagX, imagY, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Boolean value = super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x1 = event.getX();
                float y1 = event.getY();
                //phong to hinh anh
                if (x1 > mRectSquare.left && x1 < mRectSquare.right)
                    if (y1 > mRectSquare.top && y1 < mRectSquare.bottom) {
                        mCircleRadius += 20;
                        postInvalidate();
                    }
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e("AAAAADDDd"," dddd ");
                float x = event.getX();
                float y = event.getY();
                double dx = Math.pow(x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);
                //neu khoang cach tu tam den diem tram< ban kinh thi thuc hien ve
                if (dx + dy < Math.pow(mCircleRadius, 2)) {
                    mCircleY = y;
                    mCircleX = x;
                    postInvalidate();
                }

                return value;
        }

        return value;
    }

    public void swapColor() {
        mPaintSquare.setColor(mPaintSquare.getColor() == squareColor ? Color.RED : squareColor);
        postInvalidate();
    }
}
