package com.example.adminandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminandroid.Adapter.TagAdapter;
import com.example.adminandroid.Model.tag;
import com.example.adminandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TagActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TagAdapter tagAdapter;
    private List<tag> tagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize tagList
        tagList = new ArrayList<>();

        // Initialize TagAdapter
        tagAdapter = new TagAdapter(tagList, this);
        recyclerView.setAdapter(tagAdapter);

        // Read data from Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Category");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tagList.clear(); // Clear previous data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String categoryId = snapshot.getKey(); // Retrieve the category ID from the snapshot key
                    tag tag = snapshot.getValue(tag.class);
                    tag.setCategoryId(categoryId); // Set the retrieved category ID in the tag object
                    tagList.add(tag);
                }

                tagAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
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
                Intent intent = new Intent(TagActivity.this, TagCreateActivity.class);
                startActivity(intent);
            }
        });
    }
}