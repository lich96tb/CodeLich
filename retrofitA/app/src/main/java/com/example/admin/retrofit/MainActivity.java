package com.example.admin.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.retrofit.abc.DataItem;
import com.example.admin.retrofit.abc.Responsea;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private static final String BASE_URL = "https://reqres.in/api/";
    private static final String TAG ="ADSF" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RedditAPI redditAPI = retrofit.create(RedditAPI.class);

        Call<Responsea> call = redditAPI.getData();
        call.enqueue(new Callback<Responsea>() {
            @Override
            public void onResponse(Call<Responsea> call, Response<Responsea> response) {
                Log.e("ABSD ",""+response.body().toString());
                Toast.makeText(MainActivity.this, ""+response.body().getData().get(0).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Responsea> call, Throwable t) {

            }
        });
    }
}
