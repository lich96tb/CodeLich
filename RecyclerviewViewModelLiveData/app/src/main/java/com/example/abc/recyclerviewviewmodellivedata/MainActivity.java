package com.example.abc.recyclerviewviewmodellivedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.abc.recyclerviewviewmodellivedata.database.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IDelete {

    private ViewModelUser viewModelUser;
    private RecyclerView recyclerView;
    private AdapterUser adapterUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        adapterUser = new AdapterUser(this);
        recyclerView.setAdapter(adapterUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.getUserMutableLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapterUser.SetData(users);
            }
        });


    }

    public void addItem(View view) {
        User a = new User("kan", "hoa");
        List listNew = viewModelUser.getUserMutableLiveData().getValue();
        listNew.add(a);
        viewModelUser.getUserMutableLiveData().setValue(listNew);
    }

    @Override
    public void delete(int position) {
        List listNew = viewModelUser.getUserMutableLiveData().getValue();
        listNew.remove(position);
        viewModelUser.getUserMutableLiveData().setValue(listNew);
    }
}
