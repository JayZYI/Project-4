package com.example.project4.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project4.R;


public class fragmentgenre extends Fragment {
    private TextView categoryNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentgenre, container, false);

        categoryNameTextView = view.findViewById(R.id.categoryNameTextView);

        // Retrieve the category name from the arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            String categoryName = bundle.getString("categoryName");
            categoryNameTextView.setText(categoryName);
        }



        return view;
    }
}