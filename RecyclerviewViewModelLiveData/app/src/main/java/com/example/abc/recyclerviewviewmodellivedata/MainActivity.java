package com.example.abc.recyclerviewviewmodellivedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.abc.recyclerviewviewmodellivedata.database.AppDatabase;
import com.example.abc.recyclerviewviewmodellivedata.database.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDelete {

    private ViewModelUser viewModelUser;
    private RecyclerView recyclerView;
    private AdapterUser adapterUser;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        db = AppDatabase.getAppDatabase(this);
        adapterUser = new AdapterUser(this);
        recyclerView.setAdapter(adapterUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);

        //khi them vao list
//        viewModelUser.getUserMutableLiveData().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(@Nullable List<User> users) {
//                adapterUser.SetData(users);
//            }
//        });


        //them vao list khi lang ngh tu db
        viewModelUser.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapterUser.SetData(users);
            }
        });
    }


    public void addItem(View view) {
//        User a = new User("kan", "hoa");
//        List listNew = viewModelUser.getUserMutableLiveData().getValue();
//        listNew.add(a);
//        viewModelUser.getUserMutableLiveData().setValue(listNew);
    }

    @Override
    public void delete(long position) {
        //xoa tren db
        db.userDao().Delete(position);
        //delete truc tiep

//        List listNew = viewModelUser.getUserMutableLiveData().getValue();
//        listNew.remove(position);
//        viewModelUser.getUserMutableLiveData().setValue(listNew);
    }

    public void addAll(View view) {
        List<User> list = new ArrayList<>();
        list.add(new User("lich", "1"));
        list.add(new User("Lan", "2"));
        list.add(new User("Hoa", "3"));
        db.userDao().saveAll(list);
    }
}
