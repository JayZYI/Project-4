package com.example.adminandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adminandroid.Model.chapter;
import com.example.adminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChapterCreateActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextChapterTitle;
    private EditText editTextContent;
    private Button buttonSubmit;

    private DatabaseReference chapterRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_create);

        spinner = findViewById(R.id.spinner);
        editTextChapterTitle = findViewById(R.id.editTextTextPersonName);
        editTextContent = findViewById(R.id.editTextTextPersonName2);
        buttonSubmit = findViewById(R.id.button5);

        chapterRef = FirebaseDatabase.getInstance().getReference().child("chap");

        // Retrieve novel titles for the spinner
        DatabaseReference novelRef = FirebaseDatabase.getInstance().getReference().child("novel");
        novelRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> titles = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.child("title").getValue(String.class);
                    if (title != null) {
                        titles.add(title);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(ChapterCreateActivity.this,
                        android.R.layout.simple_spinner_item, titles);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChapter();
            }
        });
    }

    private void createChapter() {
        String chapterTitle = editTextChapterTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();
        String selectedNovel = spinner.getSelectedItem().toString();

        if (chapterTitle.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("ChapterCreateActivity", "Creating chapter...");
        Log.d("ChapterCreateActivity", "Chapter Title: " + chapterTitle);
        Log.d("ChapterCreateActivity", "Content: " + content);
        Log.d("ChapterCreateActivity", "Selected Novel: " + selectedNovel);

        // Generate a unique key for the new chapter
        String chapterId = chapterRef.push().getKey();

        // Create the chapter object
        chapter newChapter = new chapter();
        newChapter.setChapTitle(chapterTitle);
        newChapter.setContent(content);
        newChapter.setNovel(selectedNovel);

        // Save the new chapter to the database
        chapterRef.child(chapterId).setValue(newChapter);

        Log.d("ChapterCreateActivity", "Chapter created successfully");

        Toast.makeText(this, "Chapter created successfully", Toast.LENGTH_SHORT).show();

        // Clear the input fields
        editTextChapterTitle.setText("");
        editTextContent.setText("");

        // Finish the current activity and navigate back to the previous activity
        finish();
    }
}
