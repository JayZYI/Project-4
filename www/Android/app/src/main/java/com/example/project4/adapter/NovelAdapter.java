package com.example.project4.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project4.R;
import com.example.project4.model.NovelModel;

import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.NovelViewHolder> {

    private List<NovelModel> novelList;

    public NovelAdapter(List<NovelModel> novelList) {
        this.novelList = novelList;
    }

    @NonNull
    @Override
    public NovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rviewhomedata, parent, false);
        return new NovelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelViewHolder holder, int position) {
        NovelModel novel = novelList.get(position);
        Glide.with(holder.itemView.getContext()).load(novel.getNovelCover()).into(holder.ivNovelCover);
    }

    @Override
    public int getItemCount() {
        return novelList.size();
    }

    public static class NovelViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivNovelCover;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNovelCover = itemView.findViewById(R.id.imagehome);
            Log.d("CategoryAdapter", "CatName: " + ivNovelCover);
        }
    }
}
