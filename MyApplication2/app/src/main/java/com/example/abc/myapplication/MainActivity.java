package com.example.abc.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.abc.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        show();
        //  userViewModel = new UserViewModel();
        //nang nghe su thay doi
        userViewModel.getSoa().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, "s "+s, Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void show() {
        binding.txtShow.setText(userViewModel.getEmail().get() + "1234");
    }

    public void Clkik(View view) {
        userViewModel.getEmail().set("ADSdf!gmail.com");
        userViewModel.getName().set("do trong lich");
        binding.setViewUser(userViewModel);
        show();
    }

    public void kdjkdjdk(View view) {
        userViewModel.login();
    }
}
