package com.example.multitounch;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.Nullable;

public class Multitouch extends View {
    public Multitouch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //  poinTndex để lấy ra điểm chạm
        int pointIndex = event.getActionIndex();
        int maskAction = event.getActionMasked();
        switch (maskAction) {
            case MotionEvent.ACTION_POINTER_DOWN: {
                if (pointIndex>=1){
                    Log.e("ABC1",event.getX(0)+"");
                    Log.e("ABC2",event.getX(1)+"");
                }
            }
            case MotionEvent.ACTION_MOVE: {
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
//                    if (i>=1){
//                        Log.e("ABC1",event.getX(0)+"");
//                        Log.e("ABC2",event.getX(1)+"");
//                    }
                }
                break;
            }
        }
        return true;
    }

}
