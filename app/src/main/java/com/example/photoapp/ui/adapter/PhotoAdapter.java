package com.example.photoapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.photoapp.R;
import com.example.photoapp.data.model.PhotoModelDataClass;
import com.example.photoapp.databinding.ItemListPhotoBinding;
import com.example.photoapp.ui.listener.PhotoItemListListener;
import com.example.photoapp.utility.LoadPhoto;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private final Context context;
    private List<PhotoModelDataClass> list;
    private PhotoItemListListener listener;

    public PhotoAdapter(Context context, List<PhotoModelDataClass> list, PhotoItemListListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListPhotoBinding mBinding = ItemListPhotoBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_photo, parent, false));
        return new PhotoViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.itemListPhotoBinding.titleTextView.setText(list.get(position).getAuthor());
        holder.itemListPhotoBinding.descriptionTextView.setText(list.get(position).getDownloadUrl());
        LoadPhoto.loadNetworkPhoto(context, list.get(position).getDownloadUrl(), holder.itemListPhotoBinding.photoImageView);

        holder.itemListPhotoBinding.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updatePhotoList(List<PhotoModelDataClass> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        private final ItemListPhotoBinding itemListPhotoBinding;

        public PhotoViewHolder(@NonNull ItemListPhotoBinding itemView) {
            super(itemView.getRoot());
            itemListPhotoBinding = itemView;
        }
    }

}
