package com.example.photoapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.photoapp.data.model.PhotoModelDataClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PhotoRepository {

    private List<PhotoModelDataClass> photoDataList;
    private final static PhotoRepository mInstance = new PhotoRepository();

    public static PhotoRepository getInstance() {
        return mInstance;
    }

    public void getPhotoData(String jsonResponse) {
        photoDataList = parseJsonData(jsonResponse);
    }

    //    Parsing JsonData
    private List<PhotoModelDataClass> parseJsonData(String jsonResponse) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PhotoModelDataClass>>() {
        }.getType();
        List<PhotoModelDataClass> photoDataList = gson.fromJson(jsonResponse, listType);
        return photoDataList;
    }

    //    Providing LiveData
    public LiveData<List<PhotoModelDataClass>> fetchPhotoListLiveData() {
        MutableLiveData<List<PhotoModelDataClass>> photoListMutableData = new MutableLiveData<>();
        photoListMutableData.setValue(photoDataList);
        return photoListMutableData;
    }
}
