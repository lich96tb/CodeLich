package com.example.viewpagernoi;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer{
    private ViewPager viewPager;
    private CardAdapter cardAdapter;
    private float lastOffset;
    private boolean scalingEnabled;

    public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        this.cardAdapter = adapter;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void transformPage(@NonNull View view, float v) {

    }
}
