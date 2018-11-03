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
import com.melodymediation.sleepstories.databinding.FragmentIntroFifthBinding;
import com.melodymediation.sleepstories.interfeaces.IOnNextFragment;
import com.melodymediation.sleepstories.ui.MainActivity;
import com.melodymediation.sleepstories.ui.MainActivityMain;
import com.melodymediation.sleepstories.ui.MainFragment;
import com.melodymediation.sleepstories.utilities.ImageAnim;
import com.melodymediation.sleepstories.utilities.PrefManager;

import androidx.navigation.Navigation;

public class IntroFifthFragment extends Fragment {

    private FragmentIntroFifthBinding binding;
    public static IOnNextFragment i;
    private PrefManager prefManager;


    public IntroFifthFragment() {
    }


    public static IntroFifthFragment newInstance(IOnNextFragment a) {
        IntroFifthFragment fragment = new IntroFifthFragment();
        i = a;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_fifth, container, false);
        prefManager = new PrefManager(getContext());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageAnim.animShake(binding.imgMoonFifth);
        ImageAnim.animShake(binding.imgRelaxFifth);
        ImageAnim.animShake(binding.imgStoriesFifth);

        binding.btnNextFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setFirstTimeLaunch(false);
                Intent intent = new Intent(getContext(), MainActivityMain.class);
                startActivity(intent);
                ((Activity)getContext()).finish();
                //   Navigation.findNavController(v).navigate(R.id.mainFragment);
//                getFragmentManager().beginTransaction().replace(R.id.my_nav_host_fragment, new MainFragment()).commit();
//                prefManager.setFirstTimeLaunch(false);
                //    i.onNextFragment();
            }
        });
    }
}
