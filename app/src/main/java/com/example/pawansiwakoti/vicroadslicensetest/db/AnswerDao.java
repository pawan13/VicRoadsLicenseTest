package com.example.pawansiwakoti.vicroadslicensetest.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

import java.util.List;

@Dao
public interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Answers answers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Answers> answers);

    @Query("select * from answers")
    LiveData<List<Answers>> getAll();

    @Query("select count(*) from answers")
    LiveData<Integer> getLiveCount();

    @Query("select count(*) from answers")
    int getCount();

    @Query("delete from answers")
    int deleteAll();

    @Query("select * from answers where correctly_answered=1")
    LiveData<List<Answers>> getCorrectAnswers();

    @Query("select * from answers where skipped=1")
    LiveData<List<Answers>> getSkippedAnswers();

    @Query("select * from answers where session_id=:session")
    LiveData<List<Answers>> getAnswersBySession(String session);

    @Query("select q.* from answers a inner join quiz q on a.quiz_id = q.id where a.correctly_answered = 0 group by a.quiz_id  order by count(a.id) desc limit :maxRecords")
    LiveData<List<Quiz>> getFrequentlyWrongedQuizzes(int maxRecords);
}

