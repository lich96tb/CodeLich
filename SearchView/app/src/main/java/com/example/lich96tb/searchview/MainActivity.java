package com.example.lich96tb.searchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private EditText edt;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        edt = findViewById(R.id.edt);

        String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};


        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);
edt.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
// TODO Auto-generated method stub
        MainActivity.this.adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {
// TODO Auto-generated method stub

    }
});
    }
}
