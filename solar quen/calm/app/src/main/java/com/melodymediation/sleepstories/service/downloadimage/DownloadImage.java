package com.melodymediation.sleepstories.service.downloadimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImage extends AsyncTask<Bundle,Void,Void> {
    private String urlDist,folder;
    private Bitmap theBitmap;
    private String name;
    private ISenUrlImage iSenUrlImage;

    public DownloadImage(ISenUrlImage iSenUrlImage) {
        this.iSenUrlImage = iSenUrlImage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        folder= Environment.getExternalStorageDirectory().getAbsolutePath() +"/calm";
        File file=new File(folder);
        if (!file.exists()){
            file.mkdir();
        }
        Log.e("ACCC","bat dau");
    }

    @Override
    protected Void doInBackground(Bundle... bundles) {
        urlDist=bundles[0].getString("urlimage");
        name=bundles[0].getString("name");
        folder=folder+"/"+name+"abc.png";
        Looper.prepare();
        try {
            theBitmap = BitmapFactory.decodeStream((InputStream)new URL(urlDist).getContent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (null != theBitmap) {
            if(folder!=null){
                try (FileOutputStream out = new FileOutputStream(folder)) {
                    theBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    Log.e("adfsdlkl",""+folder);
                    iSenUrlImage.urlSenImage(folder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
    }
}
