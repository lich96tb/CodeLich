package com.example.sic100417.volleryarrayobjectinarrey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    String url = "https://api.solarjsc.vn/solarfootball/api/v1/mobile/matches/prediction?pageNo=1&pageSize=10&matchId=5b22501d8806e41becdc83ca";
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArrayRequest = response.getJSONArray("content");
                    for (int i = 0; i < jsonArrayRequest.length(); i++) {
                        JSONObject jsonObjectRequest1 = (JSONObject) jsonArrayRequest.get(i);
                        String lastModifiedDate = jsonObjectRequest1.getString("lastModifiedDate");
                        String id = jsonObjectRequest1.getString("id");
                        String userId = jsonObjectRequest1.getString("userId");
                        String matchId = jsonObjectRequest1.getString("matchId");
                        String scoreFirstTeam = jsonObjectRequest1.getString("scoreFirstTeam");
                        String scoreSecondTeam = jsonObjectRequest1.getString("scoreSecondTeam");
                        String flag = jsonObjectRequest1.getString("flag");
                        String comment = jsonObjectRequest1.getString("comment");
                        String score = jsonObjectRequest1.getString("score");
                        String cup = jsonObjectRequest1.getString("cup");
                        String avatar = jsonObjectRequest1.getString("avatar");
                        String fullName = jsonObjectRequest1.getString("fullName");
                        txt.append(lastModifiedDate + " \n" + id + " \n" + userId
                                + " \n" + matchId + " \n" + scoreFirstTeam + " \n" + scoreSecondTeam + " \n"
                                + flag + " \n" + comment + " \n" + score
                                + " \n" + cup + " \n" + avatar + " \n" + fullName + " \n" + "\n");
                    }

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
