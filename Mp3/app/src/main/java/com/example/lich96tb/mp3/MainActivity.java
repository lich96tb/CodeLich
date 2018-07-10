package com.example.lich96tb.mp3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnClick {
    int REQUEST_CODE = 10;
    List<File> listFileMp3;
    Mp3Adapter adapter;
    RecyclerView recyclerView;
    int k = 0;
    Boolean check = true;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, ServiceMusic.class);
        recyclerView = findViewById(R.id.recyclerview);
        listFileMp3 = new ArrayList<>();
        if (checkPermissions()) {
            File root = Environment.getExternalStorageDirectory();
            AddMp3(root);
            adapter = new Mp3Adapter(listFileMp3, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


        }
    }

    private void AddMp3(File root) {
        File[] listFile = root.listFiles();
        for (int i = 0; i < listFile.length; i++) {
            if (listFile[i].isDirectory() && !listFile[i].getName().equals("sound_recorder") && !listFile[i].getName().equals("testdata") && !listFile[i].getName().equals("Android") && !listFile[i].getName().equals("AudioConvertTest")) {
                AddMp3(listFile[i]);
            } else if (listFile[i].getName().endsWith(".mp3")) {
                listFileMp3.add(listFile[i]);
            }
        }
    }

    private Boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //File write logic here
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                return false;
            }
        } else {
            return true;
        }

    }

    @Override
    public void clickIntent(int position) {
        k = position;
        intent.putExtra("url", listFileMp3.get(k).getAbsolutePath());
        startService(intent);
    }

    public void clickMusic(View view) {
        switch (view.getId()) {
            case R.id.imgNext:
                stopService(intent);
                k = k + 1;
                intent.putExtra("url", listFileMp3.get(k).getAbsolutePath());
                startService(intent);
                break;
            case R.id.imgPause:
               stopService(intent);
                break;
            case R.id.imgPrevious:
                stopService(intent);
                k = k - 1;
                intent.putExtra("url", listFileMp3.get(k).getAbsolutePath());
                startService(intent);
                break;
        }
    }
}
