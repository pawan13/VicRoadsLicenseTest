package com.example.pawansiwakoti.vicroadslicensetest.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Quiz> quizzes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Quiz quiz);

    @Query("select count(*) from quiz")
    LiveData<Integer> count();

    @Query("delete from quiz")
    int deleteAll();

    @Query("select * from quiz")
    LiveData<List<Quiz>> getAll();

    @Query("select * from quiz where id == :id")
    Quiz getQuizById(String id);

    @Query("select * from quiz order by modified_at limit :max")
    LiveData<List<Quiz>> getRecentQuizes(int max);

}
