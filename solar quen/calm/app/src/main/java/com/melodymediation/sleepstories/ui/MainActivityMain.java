package com.melodymediation.sleepstories.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.service.BroadcastStopServiceSongBackground;
import com.melodymediation.sleepstories.service.ServiceMedia;
import com.melodymediation.sleepstories.service.evenbust.MessageEvent;
import com.melodymediation.sleepstories.ui.media.MediaFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import static com.melodymediation.sleepstories.ui.MethodStatic.startSongBackground;

public class MainActivityMain extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        startSongBackground(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, new MainFragment()).commit();
    }

    int k = 1;

    @Override
    public void onBackPressed() {


        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            if (k == 2) {
                Intent intent = new Intent(this, ServiceMedia.class);
                stopService(intent);
                System.exit(0);
            } else {
                if (MethodStatic.bottomSheetBehavior != null) {
                    if (MethodStatic.tabLayout.getVisibility() == View.GONE && MethodStatic.bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                        EventBus.getDefault().postSticky(new MessageEvent("EXPANDED_BACK"));
                    }
                }
                Toast.makeText(this, "Touch again to exit the app!", Toast.LENGTH_SHORT).show();
                k = k + 1;
            }

        } else {
            if (MethodStatic.bottomSheetBehavior != null) {
                if (MethodStatic.tabLayout.getVisibility() == View.GONE && MethodStatic.bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    EventBus.getDefault().postSticky(new MessageEvent("EXPANDED_BACK"));
                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MethodStatic.isMyServiceRunning(ServiceMedia.class, this) && ServiceMedia.session != null && !ServiceMedia.session.getType().equals("TYPE_MEDIA_BREATHE")&& !ServiceMedia.session.getType().equals("Background")) {
            Bundle bundle = new Bundle();
            bundle.putString("com.bigqsys.music.relax.ui.media.SESSION_ID", ServiceMedia.session.getSessionId());
            Fragment fragment = new MediaFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.player_wrap, fragment).addToBackStack("fmCategory").commit();
            Log.e("ADDDD",ServiceMedia.session.getType());
            Log.e("ADDDD",ServiceMedia.session.getName());
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
