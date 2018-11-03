package com.example.admin.doawloadlistimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.doawloadlistimage.thuvien.FileLoader;
import com.example.admin.doawloadlistimage.thuvien.builder.MultiFileDownloader;
import com.example.admin.doawloadlistimage.thuvien.listener.FileRequestListener;
import com.example.admin.doawloadlistimage.thuvien.listener.MultiFileDownloadListener;
import com.example.admin.doawloadlistimage.thuvien.pojo.FileResponse;
import com.example.admin.doawloadlistimage.thuvien.request.FileLoadRequest;
import com.example.admin.doawloadlistimage.thuvien.request.MultiFileLoadRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String k = "abc";
    private ImageView iv;
    ProgressBar progressBar;
    private TextView tvProgress;

    //chu y tao thu muc, xin quyen internet,,xin quyn doc  ghi truoc khi chay
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.image);
          progressBar = (ProgressBar) findViewById(R.id.progressbar);
          tvProgress = (TextView) findViewById(R.id.tv_progress);
        // loadImage(iv,"https://images.pexels.com/photos/45170/kittens-cat-cat-puppy-rush-45170.jpeg");


           DownloadListImageCungThuMuc();
        //DownloadListImageThuMucKhacNhau();

    }

    private void DownloadListImageThuMucKhacNhau() {
        final String[] uris = {"https://images.pexels.com/photos/45170/kittens-cat-cat-puppy-rush-45170.jpeg",
                "https://upload.wikimedia.org/wikipedia/commons/3/3c/Enrique_Simonet_-_Marina_veneciana_6MB.jpg"};
        List<MultiFileLoadRequest> multiFileLoadRequests = new ArrayList<>();
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[0], k, FileLoader.DIR_EXTERNAL_PRIVATE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[1], k, FileLoader.DIR_EXTERNAL_PUBLIC, true));

        final MultiFileDownloader multiFileDownloader = FileLoader.multiFileDownload(this);
        multiFileDownloader.checkFileintegrity(true);
        multiFileDownloader.progressListener(new MultiFileDownloadListener() {
            @Override
            public void onProgress(File downloadedFile, int progress, int totalFiles) {
                Log.e("ADDD", "fff" + downloadedFile.getAbsolutePath());
//                multiFileDownloader.cancelLoad();
                tvProgress.setText(progress + " of " + totalFiles);
                Glide.with(MainActivity.this).load(downloadedFile).into(iv);
            }
        }).loadMultiple(multiFileLoadRequests);
    }

    private void DownloadListImageCungThuMuc() {
        final String[] uris = {"https://images.pexels.com/photos/45170/kittens-cat-cat-puppy-rush-45170.jpeg",
                "https://upload.wikimedia.org/wikipedia/commons/3/3c/Enrique_Simonet_-_Marina_veneciana_6MB.jpg",
                "https://d15shllkswkct0.cloudfront.net/wp-content/blogs.dir/1/files/2017/01/Google-acquires-Fabric.png"};

        FileLoader.multiFileDownload(this)
                .fromDirectory(k, FileLoader.DIR_EXTERNAL_PUBLIC)
                .progressListener(new MultiFileDownloadListener() {
                    @Override
                    public void onProgress(File downloadedFile, int progress, int totalFiles) {
                        Glide.with(MainActivity.this).load(downloadedFile).into(iv);
                    }
                    @Override
                    public void onError(Exception e, int progress) {
                        super.onError(e, progress);
                    }
                }).loadMultiple(uris);
    }

    private void loadImage(final ImageView iv, String imageUrl) {
        iv.setImageBitmap(null);
        FileLoader.with(this)
                .load(imageUrl)
                .fromDirectory("test4", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        Bitmap bitmap = BitmapFactory.decodeFile(response.getDownloadedFile().getPath());
                        iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {

                    }
                });
    }
}
