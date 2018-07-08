package com.example.lich96tb.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.viewPage);
        //tabLayout = (TabLayout) findViewById(R.id.tablayout);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentManager adapter = new FragmentManager(manager);
        pager.setAdapter(adapter);
     //   tabLayout.setupWithViewPager(pager);
       // pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
       // tabLayout.setTabsFromPagerAdapter(adapter);
    }

    public void lich(View view) {
        Toast.makeText(this, "lichd96tb", Toast.LENGTH_SHORT).show();
    }
}
