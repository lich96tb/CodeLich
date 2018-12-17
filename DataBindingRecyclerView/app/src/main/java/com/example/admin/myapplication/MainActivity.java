package com.example.admin.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<TemperatureData> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        item = Arrays.asList(new TemperatureData("lich", "lan"),
                new TemperatureData("hoa", "han"));
        adapter = new MyAdapter(item);
        recyclerView.setAdapter(adapter);
        Event event = new Event();

        binding.setAbc(event);

    }

    public class Event {
        public void delete() {
            Toast.makeText(MainActivity.this, "adsfd", Toast.LENGTH_SHORT).show();



        }
    }
}
