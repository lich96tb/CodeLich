package com.example.lich96tb.viewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Switch;

import com.example.lich96tb.viewpager.fragment.FragmentImage;
import com.example.lich96tb.viewpager.fragment.FragmentVideo;

public class FragmentManager extends FragmentStatePagerAdapter {
    public FragmentManager(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentImage();
                break;
            case 1:
                fragment = new FragmentVideo();
                break;
        }
        return fragment;
}

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = "";
//        switch (position) {
//            case 0:
//                title = "Image";
//                break;
//            case 1:
//                title = "Video";
//                break;
//        }
//        return title;
//    }

    @Override
    public int getCount() {
        return 2;
    }
}
