package com.example.adminandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminandroid.Activity.ChapterUpdateActivity;
import com.example.adminandroid.Model.chapter;
import com.example.adminandroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private List<chapter> chapterList;
    private DatabaseReference databaseRef;

    public ChapterAdapter(List<chapter> chapterList) {
        this.chapterList = chapterList;
        databaseRef = FirebaseDatabase.getInstance().getReference("chap");
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
                deleteChapter(chap, holder.itemView.getContext());
            }
        });

        // Edit button click listener
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editChapter(chap, holder.itemView.getContext());
                Log.d("ChapterAdapter", "Retrieved Chapter Data:");
                Log.d("ChapterAdapter", "Chapter Title: " + chap.getChapTitle());
                Log.d("ChapterAdapter", "Content: " + chap.getContent());
                Log.d("ChapterAdapter", "Novel: " + chap.getNovel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    private void deleteChapter(chapter chap, Context context) {
        String chapterTitle = chap.getChapTitle();

        // Find the chapter with the matching title and delete it from the database
        databaseRef.orderByChild("chapTitle").equalTo(chapterTitle)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            snapshot.getRef().removeValue();
                        }

                        chapterList.remove(chap);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Chapter deleted successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(context, "Failed to delete chapter", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void editChapter(chapter chap, Context context) {
        // Get the chapter details
        String chapterTitle = chap.getChapTitle();
        String content = chap.getContent();
        String novel = chap.getNovel();

        // Find the chapter with the matching title and retrieve its ID
        databaseRef.orderByChild("chapTitle").equalTo(chapterTitle)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Check if the chapter exists
                        if (dataSnapshot.exists()) {
                            // Get the first child (assuming there's only one matching chapter)
                            DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
                            String chapterId = snapshot.getKey();

                            // Create an Intent to start the ChapterUpdateActivity and pass the chapter details
                            Intent intent = new Intent(context, ChapterUpdateActivity.class);
                            intent.putExtra("chapterId", chapterId); // Pass the chapter ID as an extra
                            intent.putExtra("chapterTitle", chapterTitle);
                            intent.putExtra("content", content);
                            intent.putExtra("novel", novel);
                            context.startActivity(intent);
                        } else {
                            // Handle the case when the chapter doesn't exist
                            Toast.makeText(context, "Chapter not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(context, "Failed to retrieve chapter", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
