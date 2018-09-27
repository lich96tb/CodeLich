package com.example.solar.viewmodelfragment.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.solar.viewmodelfragment.R;
import com.example.solar.viewmodelfragment.VMD;

public class fm2 extends Fragment {
    VMD vmd;
    TextView txtName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fm2, container, false);
     txtName=view.findViewById(R.id.txtName);
        vmd=ViewModelProviders.of(getActivity()).get(VMD.class);
        vmd.laydulieu().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtName.setText("Gia tri "+integer);
            }
        });

        return view;
    }

}
