package com.aaneal.databindinglivedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aaneal.databindinglivedata.databinding.ActivityMainBinding;
import com.aaneal.databindinglivedata.presenter.Presenter;
import com.aaneal.databindinglivedata.viewModel.NameModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private NameModel nameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        nameModel = ViewModelProviders.of(this).get(NameModel.class);


        activityMainBinding.setLifecycleOwner(this);

        nameModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                activityMainBinding.myText.setText(s);
            }
        });

        activityMainBinding.setPresenter(new Presenter() {
            @Override
            public void getData() {

//                third Approach
                nameModel.login();

                //this is second approch
//                nameModel.nameVal.set("this is updated one");

                //this is first approach
//                nameModel.data.setValue("this is very first approach");
            }
        });
    }
}
