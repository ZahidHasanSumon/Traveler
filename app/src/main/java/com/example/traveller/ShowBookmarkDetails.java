package com.example.traveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.traveller.Fragments.Bookmark;
import com.example.traveller.JavaClasses.BookMark;
import com.example.traveller.JavaClasses.NewUser;
import com.example.traveller.RecommendedPlaces.DetailsInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowBookmarkDetails extends AppCompatActivity {
    private ImageSlider imageSlider;
    private TextView textView, placename;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bookmark_details);
        textView = findViewById(R.id.placedetails);
        placename = findViewById(R.id.placename);
        imageSlider = findViewById(R.id.image_slider1);


        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        final String placeName = intent.getStringExtra("placeName");
        final String placeDetails = intent.getStringExtra("placeDetails");
        final String placeImage = intent.getStringExtra("placeImage");
        final String placeImage1 = intent.getStringExtra("placeImage1");
        final String placeImage2 = intent.getStringExtra("placeImage2");
        final String placeId = intent.getStringExtra("placeId");

        placename.setText(placeName);
        textView.setText(placeDetails);


        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(placeImage));
        slideModels.add(new SlideModel(placeImage1));
        slideModels.add(new SlideModel(placeImage2));

        imageSlider.setImageList(slideModels, true);
        database= FirebaseDatabase.getInstance();



    }
}
