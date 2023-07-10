package com.example.adminandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adminandroid.Model.chapter;
import com.example.adminandroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChapterUpdateActivity extends AppCompatActivity {

    private EditText editTextChapterTitle;
    private EditText editTextContent;
    private Button buttonSubmit;
    private DatabaseReference databaseRef;
    private chapter existingChapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_update);

        spinner = findViewById(R.id.spinner);
        editTextChapterTitle = findViewById(R.id.editTextTextPersonName);
        editTextContent = findViewById(R.id.editTextTextPersonName2);
        buttonSubmit = findViewById(R.id.button5);

        // Get the chapter ID from the intent extras
        String chapterId = getIntent().getStringExtra("chapterId");

        // Get the existing chapter data from the database using the chapter ID
        databaseRef = FirebaseDatabase.getInstance().getReference().child("chap").child(chapterId);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                existingChapter = dataSnapshot.getValue(chapter.class);

                // Check if the existingChapter is null
                if (existingChapter == null) {
                    Toast.makeText(ChapterUpdateActivity.this, "Chapter not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Populate the views with the existing chapter data
                editTextChapterTitle.setText(existingChapter.getChapTitle());
                editTextContent.setText(existingChapter.getContent());

                // Retrieve the novel data from the "novel" node
                DatabaseReference novelRef = FirebaseDatabase.getInstance().getReference().child("novel");
                novelRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> titles = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String title = snapshot.child("title").getValue(String.class);
                            if (title != null && !title.equals(existingChapter.getNovel())) {
                                titles.add(title);
                            }
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ChapterUpdateActivity.this,
                                android.R.layout.simple_spinner_item, titles);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                        // Set the selected item of the spinner to the retrieved novel value from the "chap" document
                        String retrievedNovel = existingChapter.getNovel();
                        int position = adapter.getPosition(retrievedNovel);
                        spinner.setSelection(position);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database error
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChapter();
            }
        });
    }

    private void updateChapter() {
        String chapterTitle = editTextChapterTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        if (chapterTitle.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the chapter data with the new values
        existingChapter.setChapTitle(chapterTitle);
        existingChapter.setContent(content);
        existingChapter.setNovel(spinner.getSelectedItem().toString());

        // Save the updated chapter data to the database
        databaseRef.setValue(existingChapter)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChapterUpdateActivity.this, "Chapter updated successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ChapterUpdateActivity.this, "Failed to update chapter", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
