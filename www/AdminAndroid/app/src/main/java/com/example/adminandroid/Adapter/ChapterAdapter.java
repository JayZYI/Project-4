package com.example.adminandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminandroid.Activity.ChapterUpdateActivity;
import com.example.adminandroid.Model.chapter;
import com.example.adminandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private List<chapter> chapterList;

    public ChapterAdapter(List<chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNovel;
        private TextView txtChapterTitle;
        private ImageButton btnDelete;
        private ImageButton btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNovel = itemView.findViewById(R.id.txt_novel);
            txtChapterTitle = itemView.findViewById(R.id.txt_title);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        chapter chap = chapterList.get(position);
        holder.txtNovel.setText(chap.getNovel());
        holder.txtChapterTitle.setText(chap.getChapTitle());

        // Delete button click listener
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteChapter(position);
            }
        });

        // Edit button click listener
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editChapter(chap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    private void deleteChapter(int position) {
        chapter chap = chapterList.get(position);
        String chapId = chap.getChapId();

        // Delete the chapter from the database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chap");
        databaseRef.child(chapId).removeValue();

        chapterList.remove(position);
        notifyItemRemoved(position);
    }

    private void editChapter(chapter chap) {

    }
}
