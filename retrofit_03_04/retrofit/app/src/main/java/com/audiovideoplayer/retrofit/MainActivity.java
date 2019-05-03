package com.audiovideoplayer.retrofit;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.audiovideoplayer.retrofit.interfaces.ILoadMore;
import com.audiovideoplayer.retrofit.model.RedditApi;
import com.audiovideoplayer.retrofit.model.ApiMusic;
import com.audiovideoplayer.retrofit.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private static final String BASE_URL = "https://api.solarjsc.vn/commonservice/api/";
    private List<ApiMusic> listAllitem = new ArrayList<>();
    private List<ApiMusic> list2 = new ArrayList<>();

    private AppDatabase db;
    private RecyclerView recyclerView;
    private AdapterLoadMusic adapterLoadMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(MainActivity.this);
        initAdapter();
        listAllitem = db.modelDao().getAll();
        for (int i = 0; i < 10; i++) {
            list2.add(listAllitem.get(i));
        }
        adapterLoadMusic.setData(list2);



        adapterLoadMusic.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                Log.e("ASDF ", "kdkdkkd");
                if (list2.size() < listAllitem.size()) {
                    list2.add(null);
                      adapterLoadMusic.setData(list2);
                    adapterLoadMusic.notifyItemInserted(list2.size() - 1);
                     //  adapterLoadMusic.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list2.remove(list2.size() - 1);
                            if (list2.size() + 5 < listAllitem.size()) {
                                Log.e("ASkkDF "," ////////////");
                                for (int i = list2.size() - 1; i < list2.size() + 5; i++) {
                                    Log.e("ASkkDF "," "+ listAllitem.get(i).getName());
                                    //   list2.add(listAllitem.get(i));
                                }
                                adapterLoadMusic.setData(list2);
                            }


                        }
                    }, 5000);

                }
            }
        });


    }

    private void initAdapter() {
        recyclerView = findViewById(R.id.recyclerview);
        adapterLoadMusic = new AdapterLoadMusic(recyclerView);
        recyclerView.setAdapter(adapterLoadMusic);


    }

    public void loadData(View view) {
        Log.e("ASDF ", " dddddddddd");
        Toast.makeText(this, "dddd", Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditApi redditApi = retrofit.create(RedditApi.class);
        Call<List<ApiMusic>> call = redditApi.getData();

        call.enqueue(new Callback<List<ApiMusic>>() {
            @Override
            public void onResponse(Call<List<ApiMusic>> call, retrofit2.Response<List<ApiMusic>> response) {
                listAllitem = response.body();
                Log.e("ASDF ", " " + listAllitem.size());
                // db.modelDao().insert(listAllitem.get(0));
                db.modelDao().insertAll(listAllitem);
            }

            @Override
            public void onFailure(Call<List<ApiMusic>> call, Throwable t) {
                Log.e("ASDF ", " loi roi:   " + t.getMessage());
            }
        });

    }

    public void loadDatass(View view) {

        Log.e("ADF ", "   " + db.modelDao().getAll().size());
        adapterLoadMusic.setData(db.modelDao().getAll());
        for (ApiMusic i : listAllitem)
            Log.e("ADF ", "   " + i.getName());
    }

}
