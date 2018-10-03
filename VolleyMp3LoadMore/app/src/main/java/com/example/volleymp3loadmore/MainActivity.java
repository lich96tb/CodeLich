package com.example.volleymp3loadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String url = "https://api.film4fun.net/api/mp3/search?pageNo=1&pageSize=10&keyword=mylove";
    private List<Music> list;
    private RecyclerView recyclerView;
    private AdapterMusic adapterMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonArrayRequest = response.getJSONObject("result");
                    JSONObject jsonObject = (JSONObject) jsonArrayRequest.get("songInfo");
                    JSONArray jsonArrayRequest1 = jsonObject.getJSONArray("songList");
                    for (int i = 0; i < jsonArrayRequest1.length(); i++) {
                        JSONObject jsonObject1 = jsonArrayRequest1.getJSONObject(i);
                        String album_title = jsonObject1.getString("album_title");
                        String artistname = jsonObject1.getString("artistname");
                        String songPic = jsonObject1.getString("songPic");
                        int songid = jsonObject1.getInt("songid");
                        String songname = jsonObject1.getString("songname");
                        String versions = jsonObject1.getString("versions");
                        list.add(new Music(album_title, artistname, songPic, songid, songname, versions));
                    }
                    adapterMusic = new AdapterMusic(list, MainActivity.this);
                    recyclerView.setAdapter(adapterMusic);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
