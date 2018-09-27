package com.example.solar.viewmodelfragment.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.solar.viewmodelfragment.R;
import com.example.solar.viewmodelfragment.VMD;

public class fm1 extends Fragment implements View.OnClickListener {
    private Button btn;
    private EditText edt;
    VMD vmd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fm1, container, false);
        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        edt = view.findViewById(R.id.edt);
        vmd = ViewModelProviders.of(getActivity()).get(VMD.class);
        return view;
    }

    @Override
    public void onClick(View view) {
        vmd.getData(Integer.parseInt(edt.getText().toString()));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frgment, new fm2());
        fragmentTransaction.addToBackStack("d");
        fragmentTransaction.commit();
    }
}
