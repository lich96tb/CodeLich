package com.example.bemboy.kjsadkbjfsdklfsdf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Broadcastabc extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("sdsdsdsd",""+intent.getAction());
        Toast.makeText(context, intent.getAction()+"", Toast.LENGTH_SHORT).show();
        if (intent.getStringExtra("flash")!=null){
            Log.e("sdsdsdsd","flash");
        }
    }
}
