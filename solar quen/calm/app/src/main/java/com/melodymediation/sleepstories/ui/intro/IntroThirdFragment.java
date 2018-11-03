package com.melodymediation.sleepstories.ui.intro;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.databinding.FragmentIntroThirdBinding;
import com.melodymediation.sleepstories.interfeaces.IOnNextFragment;
import com.melodymediation.sleepstories.utilities.ImageAnim;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroThirdFragment extends Fragment {

    public static IOnNextFragment i;
    private FragmentIntroThirdBinding binding;


    public IntroThirdFragment() {
        // Required empty public constructor
    }

    public static IntroThirdFragment newInstance(IOnNextFragment a) {
        IntroThirdFragment fragment = new IntroThirdFragment();
        i = a;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_third, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageAnim.animShake(binding.imgMoonThird);
        ImageAnim.animShake(binding.imgStoriesThird);

        binding.btnNextThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.onNextFragment();
            }
        });

    }
}
