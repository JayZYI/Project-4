package com.example.project4.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4.adapter.CategoryAdapter;
import com.example.project4.model.CategoryModel;
import com.example.project4.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fragmenthome extends Fragment implements CategoryAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryList;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmenthome, container, false);

        // Find the RecyclerView in the layout
        recyclerView = view.findViewById(R.id.rviewhome);

        // Set the layout manager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the categoryList
        categoryList = new ArrayList<>();

        // Create a new instance of the CategoryAdapter
        categoryAdapter = new CategoryAdapter((ArrayList<CategoryModel>) categoryList);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(categoryAdapter);

        // Set the item click listener on the CategoryAdapter
        categoryAdapter.setOnItemClickListener(this);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category");

        // Add a ChildEventListener to retrieve data from Firebase
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                CategoryModel category = dataSnapshot.getValue(CategoryModel.class);

                if (category != null) {
                    // Add the category to the list
                    categoryList.add(category);
                }

                // Notify the adapter that the data has changed
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle child node change if needed
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle child node removal if needed
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle child node movement if needed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
            }
        });

        return view;




    }

    @Override
    public void onItemClick(CategoryModel category) {
        // Create an instance of the GenreFragment
        fragmentgenre genreFragment = new fragmentgenre();

        // Pass the selected category to the GenreFragment
        Bundle bundle = new Bundle();
        bundle.putString("categoryName", category.getCatName());
        genreFragment.setArguments(bundle);

        // Navigate to the GenreFragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, genreFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
