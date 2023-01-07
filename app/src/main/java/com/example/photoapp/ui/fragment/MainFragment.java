package com.example.photoapp.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoapp.ApplicationClass;
import com.example.photoapp.data.api.RetrofitClient;
import com.example.photoapp.data.model.PhotoModelDataClass;
import com.example.photoapp.databinding.DialogPhotoDescriptionBinding;
import com.example.photoapp.databinding.FragmentMainBinding;
import com.example.photoapp.ui.adapter.PhotoAdapter;
import com.example.photoapp.ui.listener.PhotoItemListListener;
import com.example.photoapp.utility.LoadPhoto;
import com.example.photoapp.utility.NetworkUtility;
import com.example.photoapp.viewmodel.PhotoViewModel;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment implements PhotoItemListListener {

    private int page = 1;
    private int limit = 20;
    private FragmentMainBinding mMainBinding;
    private boolean isScrolling = false;
    private int currentItem, totalItem, scrollOutItem;
    private LinearLayoutManager layoutManager;
    private List<PhotoModelDataClass> photoDataList;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.mMainBinding = FragmentMainBinding.inflate(inflater, container, false);

        layoutManager = new LinearLayoutManager(requireActivity());
        Log.d("TAG1", "MainFragment");
        Log.d("TAG2", page + " " + limit);

        return mMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean networkAvailable = NetworkUtility.isNetworkAvailable(requireActivity());
        if (networkAvailable){
            getPhoto(page, limit);
        }else {
            mMainBinding.showOnNetworkNotAvailable.setVisibility(View.VISIBLE);
        }


        mMainBinding.imageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = layoutManager.getChildCount();
                totalItem = layoutManager.getItemCount();
                scrollOutItem = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem + scrollOutItem == totalItem)) {
                    isScrolling = false;
                    page++;
                    getPhoto(page, limit);

                }

                Log.d("OnScroll ", String.valueOf(scrollOutItem));
            }
        });
    }

    private void getPhoto(int page, int limit) {
        mMainBinding.progressCircular.setVisibility(View.VISIBLE);
        Log.d("TAG3 PhotoFun", page + " " + limit);

        //Requesting for PhotoData from RetrofitClient
        RetrofitClient.getInstance().getApi().getPhotoList(page, limit).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String photoJsonData = response.body().toString();
                    parseJsonObject(photoJsonData);
                    mMainBinding.progressCircular.setVisibility(View.GONE);
                    Log.d("TAG4.0 PhotoJsonData ", photoJsonData);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable t) {
                mMainBinding.progressCircular.setVisibility(View.GONE);
                Log.d("TAG5 RetrofitFailure", t.getLocalizedMessage());
            }
        });
    }

    private void parseJsonObject(String response) {
        PhotoViewModel viewModel = new PhotoViewModel(ApplicationClass.getInstance());
        viewModel.fetchPhotoData(response);
        viewModel.getPhotoLiveData().observe(requireActivity(), observeFinalPhotoListLiveData);
    }

    private Observer<List<PhotoModelDataClass>> observeFinalPhotoListLiveData = new Observer<List<PhotoModelDataClass>>() {

        @Override
        public void onChanged(List<PhotoModelDataClass> photoDataList) {
            if (photoDataList != null) {
                setAdapter(photoDataList);
            }
        }
    };

//    Initializing and setting adapter
    private void setAdapter(List<PhotoModelDataClass> photoDataList) {
        this.photoDataList = photoDataList;
        PhotoAdapter adapter = new PhotoAdapter(requireActivity(), photoDataList, this::onItemClickListener);
        mMainBinding.imageRecyclerView.setLayoutManager(layoutManager);
        mMainBinding.imageRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClickListener(int position) {
        PhotoModelDataClass photoModelDataClass = photoDataList.get(position);

        DialogPhotoDescriptionBinding binding = DialogPhotoDescriptionBinding.inflate(LayoutInflater.from(requireActivity()));
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(binding.getRoot());
        alertDialog.show();

        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);

        LoadPhoto.loadNetworkPhoto(requireActivity(), photoModelDataClass.getDownloadUrl(), binding.photoDetailsImageView);
        binding.titleDetailsTextView.setText(photoModelDataClass.getAuthor());
        binding.descriptionDetailsTextView.setText(photoModelDataClass.getDownloadUrl());

        binding.closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

}
