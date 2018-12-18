package com.example.abc.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.abc.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterPost adapterPost;
    private List<Post> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = binding.recyclerview;
        list = new ArrayList<>();
        list.add(new Post("asfds 10", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 2", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 3", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 4", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 5", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 6", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 7", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 8", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 9", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 10", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 11", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 12", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds13", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 5", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 6", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 7", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 8", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 9", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 10", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds 11", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));
        list.add(new Post("asfds 12", "ksjkdf", "https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg"));
        list.add(new Post("asfds13", "ksjkdf", "http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-10_044127763.jpg"));

        adapterPost = new AdapterPost(list);
        recyclerView.setAdapter(adapterPost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Clicka click = new Clicka();
        binding.setAbc(click);


    }

    public class Clicka {
        public void onClickabc() {
            list.remove(0);
            adapterPost.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "adfsd", Toast.LENGTH_SHORT).show();
        }
    }
}
