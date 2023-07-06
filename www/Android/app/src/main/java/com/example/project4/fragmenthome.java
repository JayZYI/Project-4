package com.example.project4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class fragmenthome extends Fragment {

    private RecyclerView recyclerView;
    adapterNovel adapter; // Create Object of the Adapter class
    DatabaseReference mbase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmenthome, container, false);

        recyclerView = view.findViewById(R.id.rviewhome);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a instance of the database and get its reference
        mbase = FirebaseDatabase.getInstance().getReference();

        // It is a class provided by the FirebaseUI to make a query in the database to fetch appropriate data
        FirebaseRecyclerOptions<modelNovel> options = new FirebaseRecyclerOptions.Builder<modelNovel>()
                .setQuery(mbase, modelNovel.class)
                .build();

        // Connecting object of the required Adapter class to the Adapter class itself
        adapter = new adapterNovel(options);

        // Connecting Adapter class with the Recycler view
        recyclerView.setAdapter(adapter);

        return view;
    }
}
