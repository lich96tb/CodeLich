package com.example.abc.viewmodel_databinding_livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abc.viewmodel_databinding_livedata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        MyPresenter myPresenter = new MyPresenter();
        binding.setHandlers(myPresenter);
        userViewModel.getAllData().observe(this, new Observer<CheckPassWord>() {
            @Override
            public void onChanged(@Nullable CheckPassWord checkPassWord) {
                Toast.makeText(MainActivity.this, ""+checkPassWord.getEmailCheck()+"  name "+checkPassWord.getNameCheck(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.setUserViewModel(userViewModel);


    }

    public class MyPresenter {
        public void onLisClick() {
            userViewModel.LoginUser();
            binding.setUserViewModel(userViewModel);
        }
        public void nextActivity(){
            Toast.makeText(MainActivity.this, "dddddddddd", Toast.LENGTH_SHORT).show();
        }
    }

}
