package com.melodymediation.sleepstories.service.downloadlistimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.melodymediation.sleepstories.data.room.AppDatabase;
import com.melodymediation.sleepstories.service.evenbust.MessageEventDoawloadListImage;
import com.melodymediation.sleepstories.data.room.Session;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DownLoadListImage extends AsyncTask<Void, Bundle, Void> {
    private Context context;
    private int k = 0;
    private String folder, urlDisk;
    private Bitmap theBitmap;
    private AppDatabase db;

    public DownLoadListImage(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        db = AppDatabase.Companion.getInstance(context);
        folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/calm";
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(Void... voids) {
        List<Session> sessionsaa = db.sessionDao().getSessionsByType("Background");
        for (int i = 0; i < sessionsaa.size(); i++) {
            Log.e("AC",sessionsaa.get(i).getUrlBackground());
            if (sessionsaa.get(i).getUrlBackgroundDist().equals("")) {
                urlDisk = folder + "/" + sessionsaa.get(i).getName() + "background.png";
             //   Looper.prepare();
                try {
                    theBitmap = BitmapFactory.decodeStream((InputStream) new URL(sessionsaa.get(i).getUrlBackground()).getContent());



                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    theBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();


                    Bundle bundle=new Bundle();
                    bundle.putByteArray("bitmap",byteArray);
                    bundle.putString("urldisk",urlDisk);
                    bundle.putString("id",sessionsaa.get(i).getSessionId());
                    publishProgress(bundle);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onProgressUpdate(Bundle... values) {
        super.onProgressUpdate(values);

        byte[] byteArray = values[0].getByteArray("bitmap");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        if (null != bmp) {
            if (values[0].getString("urldisk") != null) {
                try (FileOutputStream out = new FileOutputStream(values[0].getString("urldisk"))) {
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
                   db.sessionDao().updateSessionUrlBackground(values[0].getString("id"),values[0].getString("urldisk") );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        ;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        EventBus.getDefault().postSticky(new MessageEventDoawloadListImage("k"));
    }
}
