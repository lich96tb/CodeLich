package com.melodymediation.sleepstories.ui.intro;


import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.databinding.FragmentIntroFirstBinding;
import com.melodymediation.sleepstories.interfeaces.IOnNextFragment;
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailViewModel;
import com.melodymediation.sleepstories.utilities.InjectorUtils;
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFirstFragment extends Fragment {


    private FragmentIntroFirstBinding binding;
    public static IOnNextFragment i;
    private SessionDetailViewModel sessionDetailViewModel;

    public static IntroFirstFragment newInstance(IOnNextFragment a) {
        IntroFirstFragment introFirstFragment = new IntroFirstFragment();
        i = a;

        return introFirstFragment;
    }

    public IntroFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_first, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // binding.btnNextInFirst.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.introSecondFragment));
        binding.btnNextInFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.onNextFragment();

            }
        });
        checkpermission();

        // Init view model
        ViewModelProvider.Factory factory = InjectorUtils.INSTANCE.provideSessionDetailViewModelFactory(getContext());
        sessionDetailViewModel = ViewModelProviders.of(this, factory).get(SessionDetailViewModel.class);

        // Remote get categories on server and save local
//        sessionDetailViewModel.getSessionsByTypeOption(ConstantsKt.SESSION_TYPE_BACKGROUND);
    }

    private void checkpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

            }
        }
    }
}
