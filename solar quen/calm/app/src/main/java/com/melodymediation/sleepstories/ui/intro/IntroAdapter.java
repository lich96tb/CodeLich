package com.melodymediation.sleepstories.ui.intro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by AMBE on 10/3/2018 at 3:24 PM.
 */
public class IntroAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> list;

    public IntroAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);

    }

    @Override
    public int getCount() {
        return list.size();
    }


}
