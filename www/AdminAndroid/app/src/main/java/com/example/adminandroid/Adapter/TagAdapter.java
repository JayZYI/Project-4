package com.example.adminandroid.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminandroid.Activity.TagActivity;
import com.example.adminandroid.Activity.TagUpdateActivity;
import com.example.adminandroid.R;
import com.example.adminandroid.Model.tag;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private List<tag> tagList;
    private DatabaseReference databaseRef;
    private TagActivity tagActivity;


    public TagAdapter(List<tag> tagList, TagActivity tagActivity) {
        this.tagList = tagList;
        this.tagActivity = tagActivity;
        databaseRef = FirebaseDatabase.getInstance().getReference("Category");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageButton btnDelete;
        private ImageButton btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtview);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        tag tag = tagList.get(position);
        holder.textView.setText(tag.getcatName());

        // Delete button click listener
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTag(position);
            }
        });

        // Edit button click listener
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTag(tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    private void deleteTag(int position) {
        tag tag = tagList.get(position);
        String categoryId = tag.getCategoryId();

        // Delete the tag from the database
        databaseRef.child(categoryId).removeValue();

        tagList.remove(position);
        notifyItemRemoved(position);
    }

    private void editTag(tag tag) {
        Intent intent = new Intent(tagActivity, TagUpdateActivity.class);
        intent.putExtra("categoryId", tag.getCategoryId());
        intent.putExtra("catName", tag.getcatName());
        tagActivity.startActivity(intent);
    }
}
