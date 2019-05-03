package com.audiovideoplayer.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    List<Item> items = new ArrayList<>();
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Random data
        random10Data();

        //Init View
        RecyclerView recycler = (RecyclerView)findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recycler,this,items);
        recycler.setAdapter(adapter);


        //Set Load more event
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size() <= 50) // Change max size
                {
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            adapter.notifyItemRemoved(items.size());

                            //Random more data
                            int index = items.size();
                            int end = index+10;
                            for(int i=index;i<end;i++)
                            {
                                String name = UUID.randomUUID().toString();
                                Item item = new Item(name,name.length());
                                items.add(item);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    },3000); // Time to load
                }else{
                    Toast.makeText(MainActivity.this, "Load data completed !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void random10Data() {
        for(int i =0;i<10;i++)
        {
            String name = UUID.randomUUID().toString();
            Item item = new Item(name,name.length());
            items.add(item);
        }
    }
}
