package com.example.traveller.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.traveller.JavaClasses.NewUser;
import com.example.traveller.R;
import com.example.traveller.StartUp.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends Fragment {



    private TextView username,email,date;
    private Button logout;
    private CircleImageView imageView;
    private FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        username=v.findViewById(R.id.username);
        email=v.findViewById(R.id.useremail);
        date=v.findViewById(R.id.dateuser);
        logout=v.findViewById(R.id.logout);
        imageView=v.findViewById(R.id.profilepic);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);



        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("All_User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                NewUser user=dataSnapshot.getValue(NewUser.class);
                username.setText(user.getUsername());
                email.setText(user.getEmail());
                date.setText("Member Since:\n"+user.getActivationdate());
                Glide.with(getActivity()).load(user.getImageurl()).into(imageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                //mGoogleSignInClient.signOut();
                Toast.makeText(getActivity(),"You are Logged Out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), SignUpActivity.class));
                getActivity().finish();

            }
        });



        return v;
    }
}