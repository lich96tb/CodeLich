package com.melodymediation.sleepstories.data.general;

import android.content.Context;
import android.content.SharedPreferences;

public class GeneralData {
    static final String SHARED_PREFERENCES_NAME = "add";
    static final String keyShared = "checkLogin";

    public static void shared(Context context, Boolean check) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyShared, check);
        editor.commit();
    }

    public static boolean shared(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(keyShared, false);
    }
}
