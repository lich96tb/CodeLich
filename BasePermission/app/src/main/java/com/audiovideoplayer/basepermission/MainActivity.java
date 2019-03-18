package com.audiovideoplayer.basepermission;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends BaseActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] appPermissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            ,
//            Manifest.permission.ACCESS_FINE_LOCATION
        };

        if (checkAndRequestPermissions(appPermissions)) {
            // All permissions are granted already. Proceed ahead
            Log.e("ASDFSD "," xin quyen thanhc ong");
        }



    }


    @Override
    void onPermissionResult(String abd) {
        Log.e("ASDFSD "," xin quyen thanhc ong");
    }
}
