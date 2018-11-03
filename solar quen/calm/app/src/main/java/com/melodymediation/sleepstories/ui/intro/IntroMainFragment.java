package com.melodymediation.sleepstories.ui.intro;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.databinding.FragmentIntroBinding;
import com.melodymediation.sleepstories.interfeaces.IOnNextFragment;
import com.melodymediation.sleepstories.ui.MainActivityMain;
import com.melodymediation.sleepstories.utilities.PrefManager;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroMainFragment extends Fragment implements IOnNextFragment {

    private FragmentIntroBinding binding;
    private IntroAdapter mPagerAdapter;
    private List<Fragment> list;
    private PrefManager prefManager;


    public IntroMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        IntroFirstFragment introFirstFragment = IntroFirstFragment.newInstance(this);
        IntroSecondFragment introSecondFragment = IntroSecondFragment.newInstance(this);
        IntroThirdFragment introThirdFragment = IntroThirdFragment.newInstance(this);
        IntroFourthFragment introFourthFragment = IntroFourthFragment.newInstance(this);
        IntroFifthFragment introFifthFragment = IntroFifthFragment.newInstance(this);
        list.add(introFirstFragment);
        list.add(introSecondFragment);
        list.add(introThirdFragment);
        list.add(introFourthFragment);
        list.add(introFifthFragment);


        mPagerAdapter = new IntroAdapter(getChildFragmentManager(), list);
        binding.viewPager.setAdapter(mPagerAdapter);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(getContext());
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }

    }


    @Override
    public void onNextFragment() {
        if (binding.viewPager.getCurrentItem() < list.size() - 1) {
            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
        } else {
            launchHomeScreen();
        }

    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
//     Navigation.findNavController(binding.getRoot()).navigate(R.id.mainFragment);
        Intent intent = new Intent(getContext(), MainActivityMain.class);
        startActivity(intent);
        ((Activity)getContext()).finish();


    }

}
