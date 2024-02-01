package com.example.traveller.JavaClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveller.R;
import com.example.traveller.RecommendedPlaces.DetailsInfo;
import com.example.traveller.ShowBookmarkDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowBookmark extends RecyclerView.Adapter<ShowBookmark.Myholder> {
    Context context;
    List<BookMark> bookMark;
    private FirebaseDatabase database;
    DatabaseReference databaseReference,ref;
  private FirebaseAuth mAuth;

    public ShowBookmark(Context context, List<BookMark> bookMark) {
        this.context = context;
        this.bookMark = bookMark;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bookmark_show,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, final int position) {
        final String placeName=bookMark.get(position).getPlaceName();
        final String placeDetails=bookMark.get(position).getPlacesDetails();
        final String placeImage=bookMark.get(position).getImageUrl();
        final String placeImage1=bookMark.get(position).getImageUrl1();
        final String placeImage2=bookMark.get(position).getImageUrl2();
        final String PlaceId=bookMark.get(position).getPlaceId();



        try {

            Picasso.get().load(placeImage)
                    .placeholder(R.drawable.coxbazar).into(holder.image);



        }catch (Exception e){

        }
        holder.name.setText(placeName);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowBookmarkDetails.class);
                intent.putExtra("placeName",placeName);
                intent.putExtra("placeDetails",placeDetails);
                intent.putExtra("placeImage",placeImage);
                intent.putExtra("placeImage1",placeImage1);
                intent.putExtra("placeImage2",placeImage2);
                intent.putExtra("placeId",PlaceId);
                context.startActivity(intent);
                //Toast.makeText(context,PlaceId,Toast.LENGTH_LONG).show();


            }
        });


        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookMark.remove(position);
                notifyDataSetChanged();






            }
        });




    }

    @Override
    public int getItemCount() {
        return bookMark.size();
    }



    public class Myholder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private CardView cardView;
        private Button btndelete;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.placeimage);
            name=itemView.findViewById(R.id.placename);
            cardView=itemView.findViewById(R.id.placeCard);
            btndelete=itemView.findViewById(R.id.imgdelete);




        }


    }


}