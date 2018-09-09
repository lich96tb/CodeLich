package com.example.lich96tb.cropimage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CropImageView.OnSetImageUriCompleteListener,
        CropImageView.OnCropImageCompleteListener {

    private CropDemoPreset mDemoPreset;

    private CropImageView mCropImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23)
        getPermission();

        mCropImageView = findViewById(R.id.cropImageView);
        mCropImageView.setOnSetImageUriCompleteListener(this);
        mCropImageView.setOnCropImageCompleteListener(this);

        updateCurrentCropViewOptions();
        if (savedInstanceState == null) {
            if (mDemoPreset == CropDemoPreset.SCALE_CENTER_INSIDE) {
                mCropImageView.setImageResource(R.drawable.cat_small);
            } else {
//        mCropImageView.setImageResource(R.drawable.cat);
                Bitmap k= BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20180902_183011.jpg");
                mCropImageView.setImageBitmap(k);
            }
        }

    }
    private void getPermission() {
        String[] params = null;
        String writeExternalStorage = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String readExternalStorage = Manifest.permission.READ_EXTERNAL_STORAGE;

        int hasWriteExternalStoragePermission = ActivityCompat.checkSelfPermission(this, writeExternalStorage);
        int hasReadExternalStoragePermission = ActivityCompat.checkSelfPermission(this, readExternalStorage);
        List<String> permissions = new ArrayList<String>();

        if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permissions.add(writeExternalStorage);
        if (hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permissions.add(readExternalStorage);

        if (!permissions.isEmpty()) {
            params = permissions.toArray(new String[permissions.size()]);
        }
        if (params != null && params.length > 0) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    params,
                    100);
        }
    }
    public void updateCurrentCropViewOptions() {
        CropImageViewOptions options = new CropImageViewOptions();
        options.scaleType = mCropImageView.getScaleType();
        options.cropShape = mCropImageView.getCropShape();
        options.guidelines = mCropImageView.getGuidelines();
        options.aspectRatio = mCropImageView.getAspectRatio();
        options.fixAspectRatio = mCropImageView.isFixAspectRatio();
        options.showCropOverlay = mCropImageView.isShowCropOverlay();
        options.showProgressBar = mCropImageView.isShowProgressBar();
        options.autoZoomEnabled = mCropImageView.isAutoZoomEnabled();
        options.maxZoomLevel = mCropImageView.getMaxZoom();
        options.flipHorizontally = mCropImageView.isFlippedHorizontally();
        options.flipVertically = mCropImageView.isFlippedVertically();
    }
    @Override
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
        handleCropResult(result);
    }

    @Override
    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
        if (error == null) {
            Toast.makeText(this, "Image load successful", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("AIC", "Failed to load image by URI", error);
            Toast.makeText(this, "Image load failed: " + error.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }
    private void handleCropResult(CropImageView.CropResult result) {
        if (result.getError() == null) {
            Intent intent = new Intent(this, ManHnh2.class);
            intent.putExtra("SAMPLE_SIZE", result.getSampleSize());
            if (result.getUri() != null) {
                intent.putExtra("URI", result.getUri());
            } else {
                ManHnh2.mImage =
                        mCropImageView.getCropShape() == CropImageView.CropShape.OVAL
                                ? CropImage.toOvalBitmap(result.getBitmap())
                                : result.getBitmap();
            }
            startActivity(intent);
        } else {
            Log.e("AIC", "Failed to crop image", result.getError());
            Toast.makeText(
                   this,
                    "Image crop failed: " + result.getError().getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.main_action_crop) {
      mCropImageView.getCroppedImageAsync();
      return true;
    } else if (item.getItemId() == R.id.main_action_rotate) {
      mCropImageView.rotateImage(90);
      return true;
    } else if (item.getItemId() == R.id.main_action_flip_horizontally) {
      mCropImageView.flipImageHorizontally();
      return true;
    } else if (item.getItemId() == R.id.main_action_flip_vertically) {
      mCropImageView.flipImageVertically();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
