package com.example.solar.myapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    // Set your Image URL into a string
    String url = "https://i.9mobi.vn/cf/images/2015/03/nkk/nhung-hinh-anh-dep-4.jpg";
    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;
    String TAG="ASDD";
    Bitmap theBitmap;
    String filename=Environment.getExternalStorageDirectory().getAbsolutePath()+"/aaa/n.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);


    }

    public void click(View view) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Looper.prepare();
                try {
                    theBitmap = Glide.
                            with(MainActivity.this).
                            load("https://www.google.es/images/srpr/logo11w.png").
                            asBitmap().
                            into(-1,-1).
                            get();
                } catch (final ExecutionException e) {
                    Log.e(TAG, e.getMessage());
                } catch (final InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void dummy) {
                if (null != theBitmap) {
                    // The full bitmap should be available here
                    image.setImageBitmap(theBitmap);
                    Log.d(TAG, "Image loaded");


                    try (FileOutputStream out = new FileOutputStream(filename)) {
                        theBitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                        // PNG is a lossless format, the compression factor (100) is ignored
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            }
        }.execute();

    }


    }

