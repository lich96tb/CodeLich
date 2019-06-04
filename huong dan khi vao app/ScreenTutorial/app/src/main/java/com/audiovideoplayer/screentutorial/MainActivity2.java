package com.audiovideoplayer.screentutorial;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.View.OnClickListener;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

public class MainActivity2 extends AppCompatActivity implements OnClickListener {

    private ToolTipView mRedToolTipView;
    private ToolTipView mGreenToolTipView;
    private ToolTipView mBlueToolTipView;
    private ToolTipView mPurpleToolTipView;
    private ToolTipView mLich;
    private ToolTipView mOrangeToolTipView;
    private ToolTipRelativeLayout mToolTipFrameLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mToolTipFrameLayout = (ToolTipRelativeLayout) findViewById(R.id.activity_main_tooltipframelayout);
        findViewById(R.id.activity_main_redtv).setOnClickListener(this);
        findViewById(R.id.activity_main_greentv).setOnClickListener(this);
        findViewById(R.id.activity_main_bluetv).setOnClickListener(this);
        findViewById(R.id.activity_main_purpletv).setOnClickListener(this);
        findViewById(R.id.activity_main_orangetv).setOnClickListener(this);
        findViewById(R.id.btnLich).setOnClickListener(this);


    }

    private void addRedToolTipView() {
        ToolTip toolTip = new ToolTip()
                .withText("A beautiful Button")
                .withColor(getResources().getColor(R.color.holo_red))
                .withShadow();

        mRedToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.activity_main_redtv));

    }

    private void addGreenToolTipView() {
        ToolTip toolTip = new ToolTip()
                .withText("Another beautiful Button!")
                .withColor(getResources().getColor(R.color.holo_green));

        mGreenToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.activity_main_greentv));

    }

    private void addBlueToolTipView() {
        ToolTip toolTip = new ToolTip()
                .withText("Moarrrr buttons!")
                .withColor(getResources().getColor(R.color.holo_blue))
                .withAnimationType(ToolTip.AnimationType.FROM_TOP);

        mBlueToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.activity_main_bluetv));

    }

    private void addPurpleToolTipView() {
        ToolTip toolTip = new ToolTip()
                .withContentView(LayoutInflater.from(this).inflate(R.layout.custom_tooltip, null))
                .withColor(getResources().getColor(R.color.holo_purple))
                .withAnimationType(ToolTip.AnimationType.NONE);

        mPurpleToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.activity_main_purpletv));
    }


    private void addLich() {
        ToolTip toolTip = new ToolTip()
                .withContentView(LayoutInflater.from(this).inflate(R.layout.lich, null))
                .withColor(getResources().getColor(R.color.holo_purple))
                .withAnimationType(ToolTip.AnimationType.NONE);

        mLich = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.btnLich));
    }


    private void addOrangeToolTipView() {
        ToolTip toolTip = new ToolTip()
                .withText("Tap me!")
                .withColor(getResources().getColor(R.color.holo_orange));

        mOrangeToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.activity_main_orangetv));

    }

    @Override
    public void onClick(final View view) {
        int id = view.getId();
        if (id == R.id.activity_main_redtv) {
            if (mRedToolTipView == null) {
                addRedToolTipView();
            } else {
                mRedToolTipView.remove();
                mRedToolTipView = null;
            }

        } else if (id == R.id.activity_main_greentv) {
            if (mGreenToolTipView == null) {
                addGreenToolTipView();
            } else {
                mGreenToolTipView.remove();
                mGreenToolTipView = null;
            }

        } else if (id == R.id.activity_main_bluetv) {
            if (mBlueToolTipView == null) {
                addBlueToolTipView();
            } else {
                mBlueToolTipView.remove();
                mBlueToolTipView = null;
            }

        } else if (id == R.id.activity_main_purpletv) {
            if (mPurpleToolTipView == null) {
                addPurpleToolTipView();
            } else {
                mPurpleToolTipView.remove();
                mPurpleToolTipView = null;
            }

        } else if (id == R.id.activity_main_orangetv) {
            if (mOrangeToolTipView == null) {
                addOrangeToolTipView();
            } else {
                mOrangeToolTipView.remove();
                mOrangeToolTipView = null;
            }

        }else if (id==R.id.btnLich){
            if (mLich==null){
                addLich();
            }else {
                mLich.remove();
                mLich=null;
            }
        }
    }
}

