package com.example.adminandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminandroid.Model.tag;
import com.example.adminandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TagCreateActivity extends AppCompatActivity {

    private EditText editTextTagName;
    private Button buttonSubmit;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_create);

        editTextTagName = findViewById(R.id.editTextTextPersonName);
        buttonSubmit = findViewById(R.id.button5);

        // Initialize the database reference
        databaseRef = FirebaseDatabase.getInstance().getReference("Category"); // Modify the reference to "Category"

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTag();
            }
        });
    }

    private void createTag() {
        String TagName = editTextTagName.getText().toString().trim();

        if (TagName.isEmpty()) {
            Toast.makeText(this, "Please enter a tag name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a new unique tag ID
        String tagId = databaseRef.push().getKey();

        // Create a new tag object
        tag newTag = new tag();
        newTag.setCategoryId(tagId);
        newTag.setcatName(TagName);

        // Save the new tag to the database
        databaseRef.child(tagId).setValue(newTag);

        Toast.makeText(this, "Tag created successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
