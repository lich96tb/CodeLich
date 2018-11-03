package com.melodymediation.sleepstories.ui.home.background;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.data.room.Session;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> fragments;
    private List<Integer> list;
    private List<Session> backgroundsList;
    private float baseElevation;

//    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation,List<Integer> list) {
//        super(fm);
//        fragments = new ArrayList<>();
//        this.baseElevation = baseElevation;
//        this.list=list;
//
//        for(int i = 0; i< list.size(); i++){
//            addCardFragment(new CardFragment());
//        }
//    }

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation, List<Session> backgrounds) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        this.backgroundsList = backgrounds;

        for(int i = 0; i < backgroundsList.size(); i++){
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
//        fragment = new FragmentVideo();
        fragment=new CardFragment();
        Bundle bundle = new Bundle();
//        bundle.putSerializable("list", list.get(position));
        Session session = backgroundsList.get(position);
        bundle.putString("list", session.getSessionId());
        fragment.setArguments(bundle);
        ///chuyen du lieu
        //return CardFragment.getInstance(position);
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }

}
