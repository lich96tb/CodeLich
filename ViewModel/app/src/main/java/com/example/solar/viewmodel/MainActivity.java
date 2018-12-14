package com.example.solar.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt;
    private Button btn;
    private EditText edt;
    ViewModel123 viewModel123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btn.setOnClickListener(this);

    }

    private void initViews() {
        txt = findViewById(R.id.txtNumber);
        btn = findViewById(R.id.btnNumber);
        edt = findViewById(R.id.edtNumber);
        viewModel123 = ViewModelProviders.of(this).get(ViewModel123.class);


        viewModel123.getA().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txt.setText("gia chi vua nhap " + integer);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int k= Integer.parseInt(edt.getText().toString());
        viewModel123.chuyenvao(k);
    }
}
