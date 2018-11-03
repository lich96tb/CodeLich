package com.melodymediation.sleepstories.ui.home;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melodymediation.sleepstories.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategory extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_category, container, false);
    }

}
