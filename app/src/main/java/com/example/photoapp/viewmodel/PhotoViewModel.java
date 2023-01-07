package com.example.photoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.photoapp.data.model.PhotoModelDataClass;
import com.example.photoapp.data.repository.PhotoRepository;

import java.util.List;

public class PhotoViewModel extends AndroidViewModel {

    private MutableLiveData<List<PhotoModelDataClass>> photoListMutableLiveData = new MutableLiveData<>();

    public PhotoViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchPhotoData(String jsonResponse) {
        PhotoRepository.getInstance().getPhotoData(jsonResponse);
        PhotoRepository.getInstance().fetchPhotoListLiveData().observeForever(observePhotoListLiveData);
    }

    //  Observing PhotoLiveData
    private Observer<List<PhotoModelDataClass>> observePhotoListLiveData = new Observer<List<PhotoModelDataClass>>() {
        @Override
        public void onChanged(List<PhotoModelDataClass> photoModelDataList) {
            if (photoModelDataList != null) {
                photoListMutableLiveData.setValue(photoModelDataList);
            }
        }
    };

    public LiveData<List<PhotoModelDataClass>> getPhotoLiveData(){
        return photoListMutableLiveData;
    }
}
