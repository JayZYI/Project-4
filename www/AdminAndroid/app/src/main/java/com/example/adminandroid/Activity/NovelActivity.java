package com.example.adminandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adminandroid.Adapter.NovelAdapter;
import com.example.adminandroid.Model.novel;
import com.example.adminandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NovelActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NovelAdapter novelAdapter;
    private List<novel> novelList;
    private DatabaseReference databaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);


        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        novelList = new ArrayList<>();
        novelAdapter = new NovelAdapter(novelList);
        recyclerView.setAdapter(novelAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NovelActivity.this, NovelCreateActivity.class);
                startActivity(intent);
            }
        });

        databaseRef = FirebaseDatabase.getInstance().getReference("novel");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                novelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    novel novel = snapshot.getValue(novel.class);
                    novelList.add(novel);
                }
                novelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
