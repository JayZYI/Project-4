package com.example.adminandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminandroid.Activity.NovelActivity;
import com.example.adminandroid.Activity.NovelUpdateActivity;
import com.example.adminandroid.Model.novel;
import com.example.adminandroid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.ViewHolder> {
    private List<novel> novelList;
    private DatabaseReference databaseRef;
    private NovelActivity novelActivity;

    public NovelAdapter(List<novel> novelList) {
        this.novelList = novelList;
        this.novelActivity = novelActivity;
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
                deleteNovel(novel, holder.itemView.getContext());
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

    private void deleteNovel(novel novel, Context context) {
        String novelId = novel.getNovelId(); // Use the novelId instead of title

        // Find the novel with the matching ID and delete it from the database
        databaseRef.child(novelId).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        novelList.remove(novel);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Novel deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Failed to delete novel", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    private void editNovel(novel novel) {
        Intent intent = new Intent(novelActivity, NovelUpdateActivity.class);
        intent.putExtra("novelId", novel.getNovelId());
        intent.putExtra("tag", novel.getTag());
        intent.putExtra("title", novel.getTitle());
        novelActivity.startActivity(intent);
    }
}
