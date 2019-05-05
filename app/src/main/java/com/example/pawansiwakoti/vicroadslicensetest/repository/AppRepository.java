package com.example.pawansiwakoti.vicroadslicensetest.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pawansiwakoti.vicroadslicensetest.db.AppDatabase;
import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;
import com.example.pawansiwakoti.vicroadslicensetest.network.RetrofitClientInstance;
import com.example.pawansiwakoti.vicroadslicensetest.network.WebService;
import com.example.pawansiwakoti.vicroadslicensetest.FeedbackFragment;
import com.example.pawansiwakoti.vicroadslicensetest.network.FeedbackSchema;
import com.example.pawansiwakoti.vicroadslicensetest.WebServiceListener;
import com.example.pawansiwakoti.vicroadslicensetest.network.ApiResponse;
import com.example.pawansiwakoti.vicroadslicensetest.StringUtils;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton class to act as repository for data used in the app
 */
public class AppRepository {
    private static final String TAG = AppRepository.class.getSimpleName();

    private AppDatabase appDatabase;
    private WebService webService;
    private static AppRepository ourInstance;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        appDatabase = AppDatabase.getAppDatabase(context.getApplicationContext());
        webService = RetrofitClientInstance.getRetrofitInstance().create(WebService.class);
    }



    /********** Feedback ********/
    public void sendFeedback(FeedbackSchema feedback, final WebServiceListener listener) {
        Call<ApiResponse> call = webService.submitFeedback(feedback);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (listener != null) {
                    listener.onFeedbackSubmit(response.body());
                }
                Log.d(TAG, "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                if (listener != null) {
                    listener.onFeedbackSubmit(null);
                }
            }
        });
    }


    /********** Quiz ********/
    private void saveAllQuizFromApi(final List<Quiz> quizzes) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.quizDao().insertAll(quizzes);
            }
        });
    }

    public LiveData<Integer> getQuizCount() {
        return appDatabase.quizDao().count();
    }

    public LiveData<List<Quiz>> getAllQuizes() {
        return appDatabase.quizDao().getAll();
    }

    public void initiateDataFetching(boolean refreshing) {
        getDataFromApi(refreshing);
    }

    private void getDataFromApi(boolean refreshing) {
        Call<List<Quiz>> call = webService.getAllQuizes();
        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if (response.body() != null) {
                    saveAllQuizFromApi(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
            }
        });
    }

    public Quiz getQuizById(String id) {
        return appDatabase.quizDao().getQuizById(id);
    }

    public void deleteAllQuiz() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.quizDao().deleteAll();
            }
        });
    }

    public LiveData<List<Quiz>> getRecentQuizes(int max) {
        return appDatabase.quizDao().getRecentQuizes(max);
    }

    public LiveData<List<Answers>> getAllAnswers() {
        return appDatabase.answerDao().getAll();
    }

    public LiveData<List<Answers>> getAnswersBySession(String sessionId) {
        return appDatabase.answerDao().getAnswersBySession(sessionId);
    }

    public LiveData<List<Quiz>> getFrequentlyWrongedQuiz() {
        return appDatabase.answerDao().getFrequentlyWrongedQuizzes(5);
    }

    public void saveAnswers(final List<Answers> answers) {
        executor.execute(() -> appDatabase.answerDao().insertAll(answers));
    }

}