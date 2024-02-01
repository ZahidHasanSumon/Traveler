package com.example.traveller.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.traveller.ExploreDetails;
import com.example.traveller.JavaClasses.AdpterPopularPlaces;
import com.example.traveller.JavaClasses.NewUser;
import com.example.traveller.JavaClasses.PopularPlaces;
import com.example.traveller.JavaClasses.RecommendedPlaces;
import com.example.traveller.JavaClasses.RecommendedPlacesPogo;
import com.example.traveller.R;
import com.example.traveller.RecommendedPlaces.DetailsInfo;
import com.example.traveller.StartUp.SplashScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Explore extends Fragment {
    private ImageSlider imageSlider;
    private CircleImageView imageView;
    private FirebaseDatabase database;
    DatabaseReference databaseReference;
    private RecyclerView recyclerView,recyclerView1,recyclerView2;
    RecommendedPlacesPogo recommendedPlacesPogo;
    List<RecommendedPlaces> recommendedPlaces;

    AdpterPopularPlaces adpterPopularPlaces;
    List<PopularPlaces> popularPlaces;
    private String placeName,placeDetails,url,url1,url2;
    private SearchView searchView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_explore, container, false);
       imageSlider=v.findViewById(R.id.image_slider);
       imageView=v.findViewById(R.id.profilepic);

       searchView=v.findViewById(R.id.search_bar);
       //recyclerview1
       recyclerView=v.findViewById(R.id.recycle1);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       recommendedPlaces=new ArrayList<RecommendedPlaces>();

        recyclerView1=v.findViewById(R.id.recycle2);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(layoutManager);
       // recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView2=v.findViewById(R.id.searchRecycler);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));


        popularPlaces=new ArrayList<PopularPlaces>();

        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/traveller-f3bd7.appspot.com/o/Pic_Explore_Tab%2Fbichanakandi3.jpg?alt=media&token=a4be8961-732c-4242-b0dc-b401426a8b02","Bichanakandi"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/traveller-f3bd7.appspot.com/o/Pic_Explore_Tab%2Fbichanakandi2.JPG?alt=media&token=f5e252f1-bccf-46d3-98df-d1ff2320ae90","Bichanakandi"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/traveller-f3bd7.appspot.com/o/Pic_Explore_Tab%2Fjaflong1.jpg?alt=media&token=47e14fe0-15ab-42ae-8265-cbaa7616be18","Jaflong"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/traveller-f3bd7.appspot.com/o/Pic_Explore_Tab%2Fjaflong2.jpg?alt=media&token=754fabe9-8376-4884-b756-d11e440e2823","Jaflong"));


        imageSlider.setImageList(slideModels, true);

        database=FirebaseDatabase.getInstance();





        imageSlider.setItemClickListener(new ItemClickListener() {



            @Override
            public void onItemSelected(int i) {
               if(i==0){
                   databaseReference=database.getReference("Explore").child("Bichanakandi");
                   readData(databaseReference);


               }
               else if(i==1){
                    databaseReference=database.getReference("Explore").child("Bichanakandi");
                    readData(databaseReference);

                }else if (i==2){
                   databaseReference=database.getReference("Explore").child("Jaflong");
                   readData(databaseReference);

               }else if (i==3){
                   databaseReference=database.getReference("Explore").child("Jaflong");
                   readData(databaseReference);


               }
            }
        });




        database= FirebaseDatabase.getInstance();
        databaseReference=database.getReference("All_User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                NewUser user=dataSnapshot.getValue(NewUser.class);
                Glide.with(getActivity()).load(user.getImageurl()).into(imageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    recyclerView2.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
                    recyclerView2.setVisibility(View.VISIBLE);
                    searchPlaces(s);

                }else if(TextUtils.isEmpty(s.trim())){
                    recyclerView2.setVisibility(View.GONE);


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchPlaces(s);


                }else if(TextUtils.isEmpty(s.trim())){
                    recyclerView2.setVisibility(View.GONE);


                }
                return false;
            }
        });

        getAllPlaces();
        getAllPopularPlaces();



        return v;
    }

    private void getAllPlaces() {

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Recommended_Places");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recommendedPlaces.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    RecommendedPlaces places=ds.getValue(RecommendedPlaces.class);



                    recommendedPlaces.add(places);
                    recommendedPlacesPogo=new RecommendedPlacesPogo(getActivity(),recommendedPlaces);
                    recyclerView.setAdapter(recommendedPlacesPogo);



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    private void getAllPopularPlaces() {

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("PopularPlaces");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                popularPlaces.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    PopularPlaces places=ds.getValue(PopularPlaces.class);



                    popularPlaces.add(places);
                    adpterPopularPlaces=new AdpterPopularPlaces(getActivity(),popularPlaces);
                    recyclerView1.setAdapter(adpterPopularPlaces);



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void readData(DatabaseReference databaseReference){
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                PopularPlaces places=dataSnapshot.getValue(PopularPlaces.class);

                placeName=places.getPlaceName();
                placeDetails=places.getPlacesDetails();
                url=places.getImageUrl();
                url1=places.getImageUrl1();
                url2=places.getImageUrl2();


                Intent intent=new Intent(getActivity(), DetailsInfo.class);
                intent.putExtra("placeName",placeName);
                intent.putExtra("placeDetails",placeDetails);
                intent.putExtra("placeImage",url);
                intent.putExtra("placeImage1",url1);
                intent.putExtra("placeImage2",url2);
                startActivity(intent);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void searchPlaces(final String newText) {

        final FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("AllPlaces");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                popularPlaces.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    PopularPlaces places=ds.getValue(PopularPlaces.class);


                    if (places.getPlaceName().toLowerCase().contains(newText.toLowerCase())){
                        popularPlaces.add(places);

                    }




                    adpterPopularPlaces=new AdpterPopularPlaces(getActivity(),popularPlaces);
                    adpterPopularPlaces.notifyDataSetChanged();
                    recyclerView2.setAdapter(adpterPopularPlaces);



                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
