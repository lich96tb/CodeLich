package com.melodymediation.sleepstories.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.melodymediation.sleepstories.R;

/**
 * Created by AMBE on 10/12/2018 at 1:38 PM.
 */
public class CircleProgressBar extends View {


    private Paint mCircleYellow;
    private Paint mCircleBlue;
    private Paint mAcrRoundBlack;
    private Paint mAcrRoundBlue;
    private Paint mCircleThumb;

    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private RectF mArcBounds = new RectF();
    private RectF ttt = new RectF();
    private float mProgress;
    private Bitmap bitmap;
    private Paint mTextPaint;
    private Paint mTextStatusPaint;
    private final Rect mTextBounds = new Rect();
    private final Rect mTextStatusBounds = new Rect();
    private boolean start;
    private int count;
    private Ringtone ringtoneSound;


    public CircleProgressBar(Context context) {
        super(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        initPaint();
        bitmap = getBitmap(R.drawable.bg_thumb);
        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/raw/sound");
        ringtoneSound = RingtoneManager.getRingtone(getContext(), uri);
        count = 0;

    }

    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private void initPaint() {
        mCircleYellow = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleYellow.setStyle(Paint.Style.FILL);
        mCircleYellow.setColor(Color.WHITE);

        mCircleBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleBlue.setStyle(Paint.Style.FILL);
        mCircleBlue.setColor(getContext().getResources().getColor(R.color.colorDialog));


        mCircleThumb = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleThumb.setStyle(Paint.Style.STROKE);
        mCircleThumb.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mCircleThumb.setStrokeCap(Paint.Cap.ROUND);
        mCircleThumb.setColor(Color.BLACK);

        mAcrRoundBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAcrRoundBlack.setStyle(Paint.Style.STROKE);
        mAcrRoundBlack.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mAcrRoundBlack.setStrokeCap(Paint.Cap.ROUND);
        mAcrRoundBlack.setColor(Color.WHITE);

        mAcrRoundBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAcrRoundBlue.setStyle(Paint.Style.STROKE);
        mAcrRoundBlue.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mAcrRoundBlue.setStrokeCap(Paint.Cap.ROUND);
        mAcrRoundBlue.setColor(getContext().getResources().getColor(R.color.colorDialog));

        mAcrRoundBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAcrRoundBlue.setStyle(Paint.Style.STROKE);
        mAcrRoundBlue.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mAcrRoundBlue.setStrokeCap(Paint.Cap.ROUND);
        mAcrRoundBlue.setColor(getContext().getResources().getColor(R.color.colorDialog));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(100);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDialog));
        mTextPaint.setStrokeWidth(2);

        mTextStatusPaint = new Paint();
        mTextStatusPaint.setAntiAlias(true);
        mTextStatusPaint.setTextSize(40);
        mTextStatusPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextStatusPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDialog));
        mTextStatusPaint.setStrokeWidth(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;

    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
        requestLayout();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mCircleRadius = mRadius / 20;
        float width = getWidth() / 2.0f;
        float height = getWidth() / 2.0f;
        float left = (getWidth() - width) / 2.0f;
        float top = (getHeight() - height) / 2.0f;
        mArcBounds.set(left, top, left + width, top + height);
        canvas.drawCircle(mCenterX, mCenterY - width / 2.0f, mCircleRadius, mCircleYellow);
        canvas.drawCircle((float) (mCenterX + (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), mCircleRadius, mCircleYellow);
        canvas.drawCircle((float) (mCenterX - (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), mCircleRadius, mCircleYellow);

        canvas.drawArc(mArcBounds, 40f, 100f, false, mAcrRoundBlack);
        canvas.drawArc(mArcBounds, 160f, 100f, false, mAcrRoundBlack);
        canvas.drawArc(mArcBounds, 280f, 100f, false, mAcrRoundBlack);
        if (0 <= mProgress && mProgress <= 99) {
            canvas.drawArc(mArcBounds, 280f, mProgress, false, mAcrRoundBlue);
            canvas.drawCircle(mCenterX, mCenterY - width / 2.0f, mCircleRadius, mCircleBlue);

        } else if (100 <= mProgress && mProgress <= 199) {
            canvas.drawArc(mArcBounds, 40f, mProgress - 100, false, mAcrRoundBlue);
            canvas.drawArc(mArcBounds, 280f, 100f, false, mAcrRoundBlue);
            canvas.drawCircle((float) (mCenterX + (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), mCircleRadius, mCircleBlue);

        } else {
            canvas.drawArc(mArcBounds, 160f, mProgress - 200, false, mAcrRoundBlue);
            canvas.drawArc(mArcBounds, 40f, 100f, false, mAcrRoundBlue);
            canvas.drawArc(mArcBounds, 280f, 100f, false, mAcrRoundBlue);
            canvas.drawCircle((float) (mCenterX - (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), mCircleRadius, mCircleBlue);
            canvas.drawCircle((float) (mCenterX + (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), mCircleRadius, mCircleBlue);
        }
        if (mProgress == 300) {
            canvas.drawCircle(mCenterX, mCenterY - width / 2.0f, mCircleRadius, mCircleBlue);


        }

        drawTextCentred(canvas);
        invalidate();

    }

    public void drawTextCentred(Canvas canvas) {
//        if (mProgress == 300) {
//            ++count;
//
//        }
        String text = count + "";

        mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        canvas.drawText(text, mCenterX - mTextBounds.exactCenterX(), mCenterY - mTextBounds.exactCenterY() - 46, mTextPaint);


        String str = "";
        if (0 <= mProgress && mProgress <= 99) {
            str += "Breathe in";

        } else if (100 <= mProgress && mProgress <= 199) {
            str += "Hold";

        } else {
            str += "Expiratory";
        }
        mTextStatusPaint.getTextBounds(str, 0, str.length(), mTextStatusBounds);
        canvas.drawText(str, mCenterX - mTextStatusBounds.exactCenterX(), mCenterY - mTextStatusBounds.exactCenterY() + 72, mTextStatusPaint);
        invalidate();
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStart(boolean start) {
        this.start = start;
        if (start) {
            postDelayed(updateViewRunnable, DELAY_TIME_MILLIS);
        }
    }

//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//
//        if (start) {
//            animateArch();
//        }
//    }


    private class UpdateViewRunnable implements Runnable {
        public void run() {
            mProgress += 20;

            if (mProgress == 300) {
                if (ringtoneSound != null) {
                    ringtoneSound.play();
                }
                mProgress = 0;
                count++;
            }
            if (mProgress == 100 || mProgress == 200) {
                if (ringtoneSound != null) {
                    ringtoneSound.play();
                }
            }
            invalidate();


        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        start = true;
        //
    }

    @Override
    public void onDetachedFromWindow() {
        //  start = false;
        super.onDetachedFromWindow();
    }

    private static final long DELAY_TIME_MILLIS = 50L;
    private boolean updateView = false;
    private UpdateViewRunnable updateViewRunnable = new UpdateViewRunnable();

    private void animateArch() {


        ValueAnimator frontEndExtend = ValueAnimator.ofFloat(0, 300);
        frontEndExtend.setDuration(50);
        frontEndExtend.setInterpolator(new LinearInterpolator());
        frontEndExtend.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (start) {


                    if (mProgress >= 300) {
                        mProgress = 0;
                    }
                    mProgress += 1;
                    SystemClock.sleep(25);

                    invalidate();
                }
            }
        });
        if (start) {
            frontEndExtend.start();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                frontEndExtend.pause();

            }
        }
        frontEndExtend.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animateArch();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


}
