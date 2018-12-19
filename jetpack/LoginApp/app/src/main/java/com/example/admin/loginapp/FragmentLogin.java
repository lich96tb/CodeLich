package com.example.admin.loginapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.loginapp.databinding.FragmentFragmentLoginBinding;
import com.example.admin.loginapp.jetpack.User;
import com.example.admin.loginapp.jetpack.ViewModelLogin;

public class FragmentLogin extends Fragment {
   private ViewModelLogin viewModelLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentFragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fragment_login, container, false);
        viewModelLogin = ViewModelProviders.of(getActivity()).get(ViewModelLogin.class);
        ClickLogin clickLogin = new ClickLogin();
        binding.setLogin(clickLogin);

//        viewModelLogin.getUserMutableLiveData().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(@Nullable User user) {
//               // getFragmentManager().beginTransaction().replace(R.id.fragmentHome,new ShowData()).addToBackStack(null).commit();
//            }
//        });

        binding.setViewModel(viewModelLogin);


        return binding.getRoot();
    }

    public class ClickLogin {
        public void clickLogin() {
            getFragmentManager().beginTransaction().replace(R.id.fragmentHome,new ShowData()).addToBackStack(null).commit();
        //    viewModelLogin.Login();

         //   Toast.makeText(getContext(), ""+viewModelLogin.show(), Toast.LENGTH_SHORT).show();
         // getFragmentManager().beginTransaction().replace(R.id.fragmentHome,new ShowData()).addToBackStack(null).commit();
        }
    }
}
