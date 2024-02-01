package com.example.traveller.JavaClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveller.R;
import com.example.traveller.RecommendedPlaces.DetailsInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdpterPopularPlaces extends RecyclerView.Adapter<AdpterPopularPlaces.Myholder> {

    Context context;
    List<PopularPlaces> popularPlaces;

    public AdpterPopularPlaces(Context context, List<PopularPlaces> popularPlaces) {
        this.context = context;
        this.popularPlaces = popularPlaces;
    }

    @NonNull
    @Override
    public AdpterPopularPlaces.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.popularplaces,parent,false);
        return new AdpterPopularPlaces.Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterPopularPlaces.Myholder holder, int position) {

        final String placeName=popularPlaces.get(position).getPlaceName();
        final String placeDetails=popularPlaces.get(position).getPlacesDetails();
        final String placeImage=popularPlaces.get(position).getImageUrl();
        final String placeImage1=popularPlaces.get(position).getImageUrl1();
        final String placeImage2=popularPlaces.get(position).getImageUrl2();

        try {

            Picasso.get().load(placeImage)
                    .placeholder(R.drawable.coxbazar).into(holder.image);



        }catch (Exception e){

        }
        holder.name.setText(placeName);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsInfo.class);
                intent.putExtra("placeName",placeName);
                intent.putExtra("placeDetails",placeDetails);
                intent.putExtra("placeImage",placeImage);
                intent.putExtra("placeImage1",placeImage1);
                intent.putExtra("placeImage2",placeImage2);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return popularPlaces.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private CardView cardView;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.placeimage);
            name=itemView.findViewById(R.id.placename);
            cardView=itemView.findViewById(R.id.placeCard);
        }
    }
}
