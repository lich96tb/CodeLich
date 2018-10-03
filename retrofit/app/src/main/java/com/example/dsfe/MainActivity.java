package com.example.dsfe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dsfe.model.Result;
import com.example.dsfe.model.SongInfo;
import com.example.dsfe.model.SongList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "hjhj";
    private static final String TAGw = "AAA";

    private static final String BASE_URL = "https://api.film4fun.net/api/mp3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickd(View view) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<Result> call = redditAPI.getData();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e(TAGw, "onResponse: Server Response: " + response.toString());
                Log.e(TAGw, "onResponse: received information: " + response.body().getResult().getSongInfo().getSongList().get(0).getAlbumTitle());
                SongInfo songInfo=response.body().getResult().getSongInfo();
                for (int i=0;i<songInfo.getSongList().size();i++){
                    Toast.makeText(MainActivity.this, ""+songInfo.getSongList().get(i).getArtistname(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
