package com.melodymediation.sleepstories.service.downloadlistmp3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.melodymediation.sleepstories.data.room.AppDatabase;
import com.melodymediation.sleepstories.data.room.Session;
import com.melodymediation.sleepstories.service.evenbust.MessageEventDoawloadListImage;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DownloadListMp3 extends AsyncTask<Void, Bundle, Void> {
    private AppDatabase db;
    private Context context;
    private String folder, urlDisk;

    public DownloadListMp3(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        db = AppDatabase.Companion.getInstance(context);
        folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/calm";
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        List<Session> sessionsaa = db.sessionDao().getSessionsByType("Background");
        for (int i = 0; i < sessionsaa.size(); i++) {
            Log.e("ACdddd", sessionsaa.get(i).getUrl());
            if (sessionsaa.get(i).getUrlDist().equals("")) {
                urlDisk = folder + "/" + sessionsaa.get(i).getName() + "songbackground.mp3";
                try {
                    Log.e("ACdddd", sessionsaa.get(i).getUrl());
                    URL url = new URL(sessionsaa.get(i).getUrl());
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                    File file = new File(urlDisk);
                    OutputStream output = new FileOutputStream(file);
                    urlDisk = file.getAbsolutePath();
                    byte data[] = new byte[1024];
                    int count = input.read(data);
                    while (count != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", sessionsaa.get(i).getSessionId());
                    bundle.putString("urlDisk", urlDisk);
                    publishProgress(bundle);

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                }

            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Bundle... values) {
        super.onProgressUpdate(values);

        db.sessionDao().updateSessionUrlDist(values[0].getString("id"), values[0].getString("urlDisk"));

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        EventBus.getDefault().postSticky(new MessageEventDoawloadListImage("k"));
        Toast.makeText(context, "xong", Toast.LENGTH_SHORT).show();
    }
}
