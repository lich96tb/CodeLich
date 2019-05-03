package com.audiovideoplayer.retrofit.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.audiovideoplayer.retrofit.model.ApiMusic;

@Database(entities = {ApiMusic.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModelDao modelDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ajkdkf").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
