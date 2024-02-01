package com.example.traveller.RecommendedPlaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.traveller.Fragments.Bookmark;
import com.example.traveller.JavaClasses.BookMark;
import com.example.traveller.MainActivity;
import com.example.traveller.R;
import com.example.traveller.ShowBookmarkDetails;
import com.example.traveller.StartUp.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DetailsInfo extends AppCompatActivity {
    private ImageSlider imageSlider;
    private TextView textView,placename;
    private Button btnBookmark;
    private FirebaseAuth mAuth;
    private BookMark bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        textView=findViewById(R.id.placedetails);
        placename=findViewById(R.id.placename);
        imageSlider=findViewById(R.id.image_slider1);
        btnBookmark=findViewById(R.id.btnbookmark);
        mAuth = FirebaseAuth.getInstance();


        Intent intent=getIntent();
        final String placeName=intent.getStringExtra("placeName");
        final String placeDetails=intent.getStringExtra("placeDetails");
        final String placeImage=intent.getStringExtra("placeImage");
        final String placeImage1=intent.getStringExtra("placeImage1");
        final String placeImage2=intent.getStringExtra("placeImage2");

        placename.setText(placeName);
        textView.setText(placeDetails);


        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel(placeImage));
        slideModels.add(new SlideModel(placeImage1));
        slideModels.add(new SlideModel(placeImage2));

        imageSlider.setImageList(slideModels, true);

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=FirebaseDatabase.getInstance().getReference("All_User")
                        .child(mAuth.getCurrentUser().getUid()).child("Book_Mark").push().getKey();

                bookmark=new BookMark(placeName, placeDetails, placeImage, placeImage1, placeImage2,key);


                FirebaseDatabase.getInstance().getReference("All_User")
                        .child(mAuth.getCurrentUser().getUid()).child("Book_Mark").child(key).setValue(bookmark).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(DetailsInfo.this, "Successfully added", Toast.LENGTH_LONG).show();





                        } else {
                            //display a failure message
                            Toast.makeText(DetailsInfo.this,"Try again",Toast.LENGTH_LONG).show();


                        }

                    }
                });

            }
        });


    }
}