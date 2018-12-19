package com.example.admin.loginapp;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.loginapp.databinding.FragmentFragmentLoginBinding;
import com.example.admin.loginapp.databinding.FragmentShowDataBinding;
import com.example.admin.loginapp.jetpack.ViewModelLogin;

public class ShowData extends Fragment {
    private ViewModelLogin viewModelLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentShowDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_data, container, false);
        viewModelLogin = ViewModelProviders.of(getActivity()).get(ViewModelLogin.class);
        ClickShow clickShow=new ClickShow();
        binding.setClickShowData(clickShow);
        binding.setUserViewModel(viewModelLogin);
        return binding.getRoot();
    }

   public class ClickShow{
        public void clickabc(){
            Toast.makeText(getContext(), ""+viewModelLogin.show(), Toast.LENGTH_SHORT).show();
        }
    }

}
