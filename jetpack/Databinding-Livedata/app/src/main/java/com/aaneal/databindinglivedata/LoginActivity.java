package com.aaneal.databindinglivedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aaneal.databindinglivedata.databinding.ActivityLoginBinding;
import com.aaneal.databindinglivedata.model.LoginModel;
import com.aaneal.databindinglivedata.presenter.Presenter;
import com.aaneal.databindinglivedata.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        activityLoginBinding.setLoginviewmodel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        loginViewModel.response.observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(@Nullable LoginModel loginModel) {
                activityLoginBinding.txtResponse.setText(loginModel.getMessage());
            }
        });

        activityLoginBinding.setPresenter(new Presenter() {
            @Override
            public void getData() {
                loginViewModel.login();
            }
        });

    }
}
