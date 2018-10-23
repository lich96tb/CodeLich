package com.example.doawnloadmp3;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://static.solarjsc.vn/sound/sleep/music/sleep_music_01.mp3";
        String name="lichabc";
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        bundle.putString("name",name);
        new DownloadMusicFile().execute(bundle);
    }

        class DownloadMusicFile extends AsyncTask<Bundle, String, String> {
            @Override
            protected String doInBackground(Bundle... musicURL) {
                int count;
                try {
                    URL url = new URL(musicURL[0].getString("url"));
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/aaa/kfilenamedd.mp3");
                    OutputStream output = new FileOutputStream(file);
                    Log.e("aaaa",file.getAbsolutePath());

                    byte data[] = new byte[1024];

                    while ((count = input.read(data)) != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                Log.e("Error: ","ok");
                Toast.makeText(MainActivity.this, "Music Download complete.", Toast.LENGTH_SHORT).show();
            }


        }

}
