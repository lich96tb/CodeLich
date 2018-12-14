package com.example.abc.kotlinroomviewmodellivedata;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Post.class}, version = 1)
public abstract class PostsDatabase extends RoomDatabase {
    private static PostsDatabase INSTANCE;

    public abstract PostDao postDao();

    private static final Object sLock = new Object();

    public static PostsDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PostsDatabase.class, "Posts.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }

}
