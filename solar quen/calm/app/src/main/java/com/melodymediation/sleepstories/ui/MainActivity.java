package com.melodymediation.sleepstories.ui;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.databinding.ActivityMainBinding;
import com.melodymediation.sleepstories.ui.category.CategoryListViewModel;
import com.melodymediation.sleepstories.utilities.InjectorUtils;
import com.melodymediation.sleepstories.utilities.PrefManager;
import com.melodymediation.sleepstories.workers.SyncDataWorker;

import androidx.navigation.Navigation;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefManager prefManager;
    private CategoryListViewModel categoryListViewModel;
    private boolean checkPermissionWriteStorage = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkpermission();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
    }

////////////////

    private void checkpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            } else {
                event();
            }
        } else {
            event();
        }
    }

    private void event() {
        ViewModelProvider.Factory factory = InjectorUtils.INSTANCE.provideCategoryListViewModelFactory(getApplicationContext());
        categoryListViewModel = ViewModelProviders.of(this, factory).get(CategoryListViewModel.class);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
      //  binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        changeStatusBarColor();

//        // Sync data
        OneTimeWorkRequest syncData = new OneTimeWorkRequest.Builder(SyncDataWorker.class)
            .setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .addTag("SYNC_DATA_TAG")
            .build();
        WorkManager workManager = WorkManager.getInstance();

        workManager.beginWith(syncData)
            .enqueue();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    event();
                    checkPermissionWriteStorage = false;
                } else {
                    Toast.makeText(this, "You must agree permission to using app!", Toast.LENGTH_SHORT).show();
                    checkPermissionWriteStorage = true;
                    goToSettings();

                }
                break;
            }
        }
    }
    private boolean checkWriteExternalPermission() {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
    private void goToSettings() {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myAppSettings);
    }
    int h = 0;

    @Override
    protected void onResume() {
        super.onResume();
        h = h + 1;
        if (checkWriteExternalPermission() && checkPermissionWriteStorage) {
            event();
            checkPermissionWriteStorage = false;
        } else if (h == 3 && !checkWriteExternalPermission() && checkPermissionWriteStorage) {
            finish();
        }

    }
}
