package com.example.photoapp.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private final Retrofit retrofit;

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    private RetrofitClient() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtilities.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiPhotoInterface getApi() {
        return retrofit.create(ApiPhotoInterface.class);
    }

}
