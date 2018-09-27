package com.example.solar.viewmodelfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.solar.viewmodelfragment.fragment.fm1;

public class MainActivity extends AppCompatActivity {
    VMD vmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frgment, new fm1());
        fragmentTransaction.addToBackStack("d");
        fragmentTransaction.commit();

//        vmd = ViewModelProviders.of(this).get(VMD.class);
//        vmd.laydulieu().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(@Nullable Integer integer) {
//
//            }
//        });
    }
}
