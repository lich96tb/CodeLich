package com.example.lich96tb.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lich96tb.volley.server.Hang;
import com.example.lich96tb.volley.server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class ManHinh2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        Toast.makeText(this, "gia tri id"+ Hang.id, Toast.LENGTH_SHORT).show();
        try {
            layData();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void layData() throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("UserID",Hang.id);
        jsonObject.put("PageNum",1);
        jsonObject.put("PageSize",30);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, Server.get, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Gia ",""+response);
                try {
                    JSONObject data=response.getJSONObject("Data");
                    JSONArray listData=data.getJSONArray("ListProduct");
                    Log.e("ListData",listData+"");
                    for (int i=0;i<listData.length();i++){
                        String name=listData.getJSONObject(i).getString("Name");
                        Toast.makeText(ManHinh2.this, "ten la "+name, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ManHinh2.this, "loi "+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
