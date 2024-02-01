package com.example.traveller.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveller.JavaClasses.BookMark;
import com.example.traveller.JavaClasses.RecommendedPlaces;
import com.example.traveller.JavaClasses.RecommendedPlacesPogo;
import com.example.traveller.JavaClasses.ShowBookmark;
import com.example.traveller.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Bookmark extends Fragment {
    private RecyclerView recyclerView;
    private ShowBookmark showBookmark;
    List<BookMark> bookMark;

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_bookmark, container, false);

       recyclerView=v.findViewById(R.id.bookmarkRecycler);
        mAuth = FirebaseAuth.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookMark=new ArrayList<BookMark>();


        getAllPlaces();






       return v;
    }


    private void getAllPlaces() {


        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference("All_User").child(mAuth.getCurrentUser().getUid()).child("Book_Mark");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookMark.clear();


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    BookMark places=ds.getValue(BookMark.class);

                    bookMark.add(places);
                    showBookmark=new ShowBookmark(getActivity(),bookMark);
                    recyclerView.setAdapter(showBookmark);


                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









    }




}