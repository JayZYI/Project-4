package com.example.adminandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminandroid.Model.novel;
import com.example.adminandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.ViewHolder> {
    private List<novel> novelList;
    private DatabaseReference databaseRef;

    public NovelAdapter(List<novel> novelList) {
        this.novelList = novelList;
        databaseRef = FirebaseDatabase.getInstance().getReference("novel");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTag;
        private TextView txtTitle;
        private ImageButton btnDelete;
        private ImageButton btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTag = itemView.findViewById(R.id.txtTag);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_novel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        novel novel = novelList.get(position);
        holder.txtTag.setText(novel.getTag());
        holder.txtTitle.setText(novel.getTitle());

        // Delete button click listener
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNovel(position);
            }
        });

        // Edit button click listener
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNovel(novel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return novelList.size();
    }

    private void deleteNovel(int position) {
        novel novel = novelList.get(position);
        String novelId = novel.getNovelId();

        // Delete the novel from the database
        databaseRef.child(novelId).removeValue();

        novelList.remove(position);
        notifyItemRemoved(position);
    }

    private void editNovel(novel novel) {
        // Implement your logic for editing a novel here
    }
}
