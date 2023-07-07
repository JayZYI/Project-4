package com.example.adminandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.NovelAdapter;
import mahasiswa.CreateActivity;

import mahasiswa.CreateTagActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Button btnAdd;
    private Button btnNovelAdd; // New button for novel addition

    private NovelAdapter adapter;
    private ArrayList<Novel> NovelList;
    DatabaseReference dbNovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbNovel = FirebaseDatabase.getInstance().getReference("Novel");

        listView = findViewById(R.id.lv_list);
        btnAdd = findViewById(R.id.btn_add);
        btnNovelAdd = findViewById(R.id.btnnovel_add); // Assign the button ID for novel addition
        btnAdd.setOnClickListener(this);
        btnNovelAdd.setOnClickListener(this); // Set onClickListener for the new button

        NovelList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_MAHASISWA, NovelList.get(i));

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbNovel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NovelList.clear();

                for (DataSnapshot NovelSnapshot : dataSnapshot.getChildren()) {
                    Novel Novel = NovelSnapshot.getValue(Novel.class);
                    NovelList.add(Novel);
                }

                NovelAdapter adapter = new NovelAdapter(MainActivity.this);
                adapter.setNovelList(NovelList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            Intent intent = new Intent(MainActivity.this, CreateTagActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btnnovel_add) { // Handle the new button click event
            Intent intent = new Intent(MainActivity.this, CreateNovelActivity.class);
            startActivity(intent);
        }
    }
}
