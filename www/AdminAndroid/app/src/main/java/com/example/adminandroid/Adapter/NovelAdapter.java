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
                editNovel(novel, holder.itemView.getContext());
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


    private void editNovel(novel novel, Context context) {
        String title = novel.getTitle();
        String tag = novel.getTag();
        String novelCover = novel.getNovelCover();
        String views = novel.getViews();
        String chapters = novel.getChapters();
        String readTimes = novel.getReadTimes();

        Log.d("NovelAdapter", "Title: " + title);
        Log.d("NovelAdapter", "Tag: " + tag);
        Log.d("NovelAdapter", "Novel Cover: " + novelCover);
        Log.d("NovelAdapter", "Views: " + views);
        Log.d("NovelAdapter", "Chapters: " + chapters);
        Log.d("NovelAdapter", "Read Times: " + readTimes);

        databaseRef.orderByChild("title").equalTo(title)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Get the first child (assuming there's only one matching chapter)
                            DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
                            String novelId = snapshot.getKey();

                            // Create an Intent to start the ChapterUpdateActivity and pass the chapter details
                            Intent intent = new Intent(context, NovelUpdateActivity.class);
                            intent.putExtra("novelId", novelId); // Pass the chapter ID as an extra
                            intent.putExtra("title", title);
                            intent.putExtra("tag", tag);
                            intent.putExtra("novelCover", novelCover);
                            intent.putExtra("views", views);
                            intent.putExtra("chapters", chapters);
                            intent.putExtra("readTimes", readTimes);
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
