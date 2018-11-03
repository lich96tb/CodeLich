package com.melodymediation.sleepstories.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AMBE on 10/4/2018 at 8:41 AM.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "CALM";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String IS_FIRST_TIME_SYNC_DATA = "IsFirstTimeSyncData";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeSyncData(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_SYNC_DATA, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeSyncData() {
        return pref.getBoolean(IS_FIRST_TIME_SYNC_DATA, true);
    }
}
