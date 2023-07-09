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

public class TagUpdateActivity extends AppCompatActivity {
    private EditText editTextCatName;
    private Button buttonUpdate;
    private DatabaseReference databaseRef;
    private String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_update);

        // Retrieve the values from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoryId = extras.getString("categoryId");
            String catName = extras.getString("catName");

            // Initialize UI elements
            editTextCatName = findViewById(R.id.update_name);
            buttonUpdate = findViewById(R.id.btn_update);

            // Set the initial value for the catName EditText
            editTextCatName.setText(catName);
        }

        // Initialize the database reference
        databaseRef = FirebaseDatabase.getInstance().getReference("Category");

        // Set click listener for the update button
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTag();
            }
        });
    }

    private void updateTag() {
        String updatedCatName = editTextCatName.getText().toString().trim();

        // Perform validation if needed

        // Update the tag in the database
        if (categoryId != null) {
            databaseRef.child(categoryId).child("CatName").setValue(updatedCatName);
            Toast.makeText(TagUpdateActivity.this, "Tag updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(TagUpdateActivity.this, "Failed to update tag", Toast.LENGTH_SHORT).show();
        }
    }
}
