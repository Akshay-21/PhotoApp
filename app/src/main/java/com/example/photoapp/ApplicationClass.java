package com.example.photoapp;

import android.app.Application;

public class ApplicationClass extends Application {

    private static ApplicationClass mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ApplicationClass getInstance() {
        return mInstance;
    }
}
