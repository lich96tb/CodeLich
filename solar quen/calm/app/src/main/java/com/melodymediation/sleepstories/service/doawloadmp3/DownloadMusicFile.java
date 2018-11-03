package com.melodymediation.sleepstories.service.doawloadmp3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadMusicFile extends AsyncTask<Bundle, String, String> {
    private String urlDist,folder;
    private IDoawnload doawnload;

    public DownloadMusicFile(com.melodymediation.sleepstories.service.doawloadmp3.IDoawnload doawnload) {
        this.doawnload = doawnload;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        folder=Environment.getExternalStorageDirectory().getAbsolutePath() +"/calm";
        File file=new File(folder);
        if (!file.exists()){
            file.mkdir();
        }
    }

    @Override
    protected String doInBackground(Bundle... bundles) {
        int count;
        try {
            URL url = new URL(bundles[0].getString("url"));
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            File file = new File(folder+"/"+bundles[0].getString("name")+".mp3");
            OutputStream output = new FileOutputStream(file);
            urlDist=file.getAbsolutePath();

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
        doawnload.Idoawload(urlDist);

    }
}
