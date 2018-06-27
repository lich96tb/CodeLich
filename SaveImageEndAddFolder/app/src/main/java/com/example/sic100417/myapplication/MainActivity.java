package com.example.sic100417.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddFolderImage();
    }
    // add folder Image
    private void AddFolderImage() {
        File myDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Loa12gdd3");
        myDir.mkdirs();
        String fileName = "image_2.jpeg";
        File file = new File(myDir, fileName);
        try {
            Drawable drawable = getResources().getDrawable(R.drawable.f);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            FileOutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outStream);
            outStream.flush();
            Toast.makeText(this, "lu anh thanh cong", Toast.LENGTH_SHORT).show();
            MediaScannerConnection.scanFile(this,
                    new String[]{file.getPath()},
                    new String[]{"image/jpeg"}, null);

            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
