package com.example.pawansiwakoti.vicroadslicensetest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

@Database(entities = {Quiz.class, Answers.class}, version = 2)
@TypeConverters({DateConverter.class, ArrayConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "viclicencequiz";
    public abstract QuizDao quizDao();
    public abstract AnswerDao answerDao();
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
