package com.example.traveller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ExploreDetails extends AppCompatActivity {
    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_details);
        tx=findViewById(R.id.text);
        Intent intent=getIntent();
        String placeName=intent.getStringExtra("placeName");
        String placeDetails=intent.getStringExtra("placeDetails");
        String placeImage=intent.getStringExtra("placeImage");
        String placeImage1=intent.getStringExtra("placeImage1");
        String placeImage2=intent.getStringExtra("placeImage2");

       tx.setText(placeName+placeDetails);
    }
}