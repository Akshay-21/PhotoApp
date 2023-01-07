package com.example.photoapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.photoapp.data.api.RetrofitClient;
import com.example.photoapp.databinding.ActivityMainBinding;
import com.example.photoapp.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(mBinding.container.getId(), MainFragment.newInstance())
                    .commit();
        }


//        RetrofitClient.getInstance().getApi();

    }
} 