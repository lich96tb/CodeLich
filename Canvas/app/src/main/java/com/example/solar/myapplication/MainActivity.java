package com.example.solar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.GatewayInfo;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private GraphicView graphicView;
int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphicView=findViewById(R.id.bacsdf);

    }

    public void ADSfds(View view) {
        graphicView.setValue(k);
        k=k+1;
    }
}
