//package com.example.project4.activity;
//
//import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import com.example.project4.adapter.NovelAdapter;
//        import com.example.project4.model.NovelModel;
//        import com.google.firebase.database.DataSnapshot;
//        import com.google.firebase.database.DatabaseError;
//        import com.google.firebase.database.DatabaseReference;
//        import com.google.firebase.database.FirebaseDatabase;
//        import com.google.firebase.database.ValueEventListener;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class imagerview {
//    private RecyclerView recyclerView;
//    private NovelAdapter novelAdapter;
//    private DatabaseReference databaseReference;
//
//    public imagerview(Context context, RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        novelAdapter = new NovelAdapter(new ArrayList<>());
//        recyclerView.setAdapter(novelAdapter);
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("novel");
//        fetchDataFromFirebase();
//    }
//
//    private void fetchDataFromFirebase() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<NovelModel> novelList = new ArrayList<>();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    NovelModel novel = snapshot.getValue(NovelModel.class);
//                    if (novel != null) {
//                        novelList.add(novel);
//                    }
//                }
//
//                novelAdapter.setData(novelList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle any errors that occur during data retrieval
//            }
//        });
//    }
//}
