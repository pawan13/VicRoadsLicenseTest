package com.example.pawansiwakoti.vicroadslicensetest.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "vicroad-license-quiz.db";
    public abstract QuizDao quizDao();
    private static final Object LOCK = new Object();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}