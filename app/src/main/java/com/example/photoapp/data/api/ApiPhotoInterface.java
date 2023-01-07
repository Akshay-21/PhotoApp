package com.example.photoapp.data.api;

import com.example.photoapp.data.model.PhotoModelDataClass;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiPhotoInterface {
    @GET("v2/list")
    Call<JsonArray> getPhotoList(@Query("page") int page, @Query("limit") int limit);
}
