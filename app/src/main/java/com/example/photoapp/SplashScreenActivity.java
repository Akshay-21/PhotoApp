package com.example.photoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.photoapp.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding mBinding;
    private static final int SPLASH_SCREEN_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        startMainActivity();
    }

    private void startMainActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            SplashScreenActivity.this.finish();
        }, SPLASH_SCREEN_TIME);
    }
}