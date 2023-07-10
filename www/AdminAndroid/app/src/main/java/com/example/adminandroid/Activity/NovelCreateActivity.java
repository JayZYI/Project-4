package com.example.adminandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminandroid.Model.novel;
import com.example.adminandroid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class NovelCreateActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText editTextTitle;
    private Button buttonOpenGallery;
    private Button buttonSubmit;
    private Spinner spinnerCategory;
    private DatabaseReference databaseRef;
    private StorageReference storageRef;
    private Uri selectedImageUri;
    private TextView textViewImageName; // TextView to display the image name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_create);

        editTextTitle = findViewById(R.id.editTextTextPersonName);
        buttonOpenGallery = findViewById(R.id.btnOpenGallery);
        buttonSubmit = findViewById(R.id.button5);
        spinnerCategory = findViewById(R.id.spinner);
        textViewImageName = findViewById(R.id.textViewImageName); // Initialize the TextView

        databaseRef = FirebaseDatabase.getInstance().getReference("novel");
        storageRef = FirebaseStorage.getInstance().getReference().child("novel_covers");

        populateSpinner();

        buttonOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNovel();
            }
        });
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();

            // Get the image name from the Uri and display it in the TextView
            String imageName = getFileName(selectedImageUri);
            textViewImageName.setText(imageName);
        }
    }

    private void populateSpinner() {
        DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference("Category");
        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> categoryList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String catName = snapshot.child("catName").getValue(String.class);
                    categoryList.add(catName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(NovelCreateActivity.this, android.R.layout.simple_spinner_item, categoryList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategory.setAdapter(adapter);
                Log.d("NovelCreateActivity", "Spinner Data: " + categoryList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    private void createNovel() {
        String title = editTextTitle.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a novel title", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select a novel cover", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference imageRef = storageRef.child(System.currentTimeMillis() + ".jpg");

        imageRef.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get the download URL of the uploaded image
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUri) {
                                // Create a new novel object
                                DatabaseReference newNovelRef = databaseRef.push();
                                String novelId = newNovelRef.getKey();
                                novel newNovel = new novel();
                                newNovel.setNovelId(novelId);
                                newNovel.setTitle(title);
                                newNovel.setTag(category);
                                newNovel.setNovelCover(downloadUri.toString());
                                newNovel.setChapters("0"); // Set chapters to 0
                                newNovel.setReadTimes("0"); // Set readTimes to 0
                                newNovel.setViews("0"); // Set views to 0

                                // Save the new novel to the database
                                newNovelRef.setValue(newNovel);

                                // Log the data being stored
                                Log.d("NovelCreateActivity", "Novel Title: " + newNovel.getTitle());
                                Log.d("NovelCreateActivity", "Novel Tag: " + newNovel.getTag());
                                Log.d("NovelCreateActivity", "Novel Cover: " + newNovel.getNovelCover());
                                Log.d("NovelCreateActivity", "Chapters: " + newNovel.getChapters());
                                Log.d("NovelCreateActivity", "Read Times: " + newNovel.getReadTimes());
                                Log.d("NovelCreateActivity", "Views: " + newNovel.getViews());

                                Toast.makeText(NovelCreateActivity.this, "Novel created successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NovelCreateActivity.this, "Failed to upload novel cover", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String getFileName(Uri uri) {
        String fileName = null;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            fileName = cursor.getString(nameIndex);
        }
        if (cursor != null) {
            cursor.close();
        }
        return fileName;
    }
}
