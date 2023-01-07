package com.example.photoapp.utility;

public class DemoClass {
/*    private void retrofitClient(int page, int limit) {
        Log.d("TAG4 RetrofitClient", page + " " + limit);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://picsum.photos/").addConverterFactory(GsonConverterFactory.create()).build();

        ApiPhotoInterface photoInterface = retrofit.create(ApiPhotoInterface.class);
        Call<JsonArray> call = photoInterface.getPhotoList(page, limit);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                //hide progress bar
                if (response.isSuccessful() && response.body() != null) {

                    String photoJsonData = response.body().toString();
                    Log.d("TAG4.0 PhotoJsonData ", photoJsonData);
                    parseJsonObject(photoJsonData);
                    mMainBinding.progressCircular.setVisibility(View.GONE);
                    //progress bar hide
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable t) {
                //hide progress bar
                Log.d("TAG5 RetrofitFailure", String.valueOf(call));
                Log.d("TAG6 RetrofitFailure", t.getLocalizedMessage());
                Log.d("TAG7 RetrofitFailure", Arrays.toString(t.getStackTrace()));
            }
        });
    }*/

}

class ABC {
   /* public static final String BASE_URL = "";
    public static Retrofit retrofit = null;

    //  Creating RetrofitClient
    public static ApiPhotoInterface getApiPhotoInterface() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiPhotoInterface.class);
    }*/

    //        Glide.with(context).load(list.get(position).getDownloadUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.itemListPhotoBinding.photoImageView);
}
