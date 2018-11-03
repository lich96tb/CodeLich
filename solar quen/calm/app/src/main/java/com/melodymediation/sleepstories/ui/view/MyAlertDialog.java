package com.melodymediation.sleepstories.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Created by AMBE on 10/5/2018 at 3:51 PM.
 */
public abstract class MyAlertDialog extends AlertDialog {
    private OnBackPressListener backPressListener;


    public MyAlertDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayout());
        ButterKnife.bind(this);
        setupViews();
        setAnimation();
    }


    protected abstract int provideLayout();

    protected abstract void setupViews();

    protected abstract void setAnimation();

    @Override
    protected void onStart() {
        super.onStart();
        setupLayout();
    }

    private void setupLayout() {
        if (getWindow() != null)
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        try {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            requestWindowFeature(Window.FEATURE_NO_TITLE);


        } catch (Exception ignored) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (backPressListener != null)
            backPressListener.onBackPress();
    }

    protected void release() {
        backPressListener = null;
    }


    public void setBackPressListener(OnBackPressListener backPressListener) {
        this.backPressListener = backPressListener;
    }

    public interface OnBackPressListener {
        void onBackPress();
    }

}
