package com.melodymediation.sleepstories.ui;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.melodymediation.sleepstories.service.BroadcastStopServiceSongBackground;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class MethodStatic {
    public static String SHARED_PREFERENCES_NAME="Login";
    public static String TIMEBACKGROUND="ADDD";
    public static TabLayout tabLayout;
    public static BottomSheetBehavior bottomSheetBehavior;

    public static void checklogin(Context context, Boolean checkLogin){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",checkLogin);
        editor.commit();
    }
    public static boolean checklogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean("login",false);
    }

    public static void timeBackground(Context context,long time){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(TIMEBACKGROUND,time);
        editor.commit();
    }
    public static long timeBackground(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getLong(TIMEBACKGROUND,30*60000);
    }
     public static  void AlarmSoungBackground(Context context,long time){
        Calendar calendar=Calendar.getInstance();
        time=calendar.getTimeInMillis()+time;
         Intent intentBroadcastStopService = new Intent(context, BroadcastStopServiceSongBackground.class);
         AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
         PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentBroadcastStopService, PendingIntent.FLAG_UPDATE_CURRENT);
         alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
         Log.e("ACDddDss",""+ time);
     }
    public static boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public static void startSongBackground(Context context){
        Calendar calendar = Calendar.getInstance();
        Long time = calendar.getTimeInMillis() + MethodStatic.timeBackground(context);
        Intent intent = new Intent(context, BroadcastStopServiceSongBackground.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

}
