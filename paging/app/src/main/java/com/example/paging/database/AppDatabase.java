package com.example.paging.database;

import android.arch.persistence.room.Database;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase {
    public static final String DATABASE_NAME = "UserDb";

    public abstract UserDao userDao();
}
