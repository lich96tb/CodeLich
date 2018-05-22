package com.example.cntt.myapplication;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Click {
    private RecyclerView recyclerView;
    AdapterStudent adapterStudent;
    List<Student> listData;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rycyclervieew);
        listData = new ArrayList<>();
        int h=0;
        for (int i=0;i<3;i++) {
            listData.add(new Student("lcih"+(h=h+1), 1));
            listData.add(new Student("lan"+(h=h+1), 2));
            listData.add(new Student("hoa"+(h=h+1), 4));
            listData.add(new Student("luon"+(h=h+1), 5));
            listData.add(new Student("luoen"+(h=h+1), 5));
            listData.add(new Student("van luyen "+(h=h+i), 52));
        }
        adapterStudent = new AdapterStudent(listData,this);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //hien thị tạ vị trí mong muốn
        linearLayoutManager.scrollToPosition(2);
        // tạo đường kẻ ngang khoảng cách giữa các item
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.ic_launcher_background));//set trực tiếp hình ảnh vào khoảng cách
        recyclerView.addItemDecoration(dividerItemDecoration);


        recyclerView.setAdapter(adapterStudent);

    }

    public void onclick(View view) {
        Toast.makeText(this, "ket qua la gif "+linearLayoutManager.findViewByPosition(5), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickView(int k) {
        Toast.makeText(this, "vi chi"+k, Toast.LENGTH_SHORT).show();
    }
}
