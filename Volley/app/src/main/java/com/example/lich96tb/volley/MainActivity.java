package com.example.lich96tb.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lich96tb.volley.server.Hang;
import com.example.lich96tb.volley.server.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private EditText edtMail,edtPass;
private String mail,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMail=findViewById(R.id.edt_mail);
        edtPass=findViewById(R.id.edt_pass);
    }

    public void click(View view) throws JSONException {
        Server server=new Server();
        mail=edtMail.getText().toString();
        pass=edtPass.getText().toString();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Email",mail);
        jsonObject.put("Password",pass);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, server.login, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String aa = response.getString("Description");
                   JSONObject data=response.getJSONObject("Data");
                   int id=data.getInt("ID");
                    Toast.makeText(MainActivity.this, aa, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,ManHinh2.class);
                  //  intent.putExtra("id",id);
                    Hang hang=new Hang();
                    hang.id=id;
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "loi roi"+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
