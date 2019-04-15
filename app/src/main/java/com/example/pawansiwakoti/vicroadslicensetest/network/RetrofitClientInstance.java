package com.example.pawansiwakoti.vicroadslicensetest.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://viclicencequiz.herokuapp.com";
    private static OkHttpClient.Builder httpClient;
    private static HttpLoggingInterceptor logging;

    public static Retrofit getRetrofitInstance() {
        if (logging == null) {
            logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder();
        }

        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
