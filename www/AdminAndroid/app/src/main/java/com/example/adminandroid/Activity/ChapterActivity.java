package com.example.adminandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adminandroid.Adapter.ChapterAdapter;
import com.example.adminandroid.MainActivity;
import com.example.adminandroid.Model.chapter;
import com.example.adminandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;
    private List<chapter> chapterList;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chapterList = new ArrayList<>();
        chapterAdapter = new ChapterAdapter(chapterList);
        recyclerView.setAdapter(chapterAdapter);

        databaseRef = FirebaseDatabase.getInstance().getReference("chap");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chapterList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chapter chap = snapshot.getValue(chapter.class);
                    chapterList.add(chap);
                }
                chapterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChapterActivity.this, ChapterCreateActivity.class);
                startActivity(intent);
            }
        });
    }
}
