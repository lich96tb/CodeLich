package com.example.abc.viewmodelabc.screentow;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.abc.viewmodelabc.MainActivity;
import com.example.abc.viewmodelabc.R;

public class Screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        if (savedInstanceState==null)
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmMain,new FragmentEdit()).commit();

    }


}
