package com.example.traveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.traveller.Fragments.Bookmark;
import com.example.traveller.Fragments.Explore;
import com.example.traveller.Fragments.Profile;
import com.example.traveller.Fragments.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView nav;
    private FrameLayout frame;
    private Bookmark bookmark;
    private Explore explore;
    private Profile profile;
    private Settings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        nav=findViewById(R.id.bottom2);
        frame=findViewById(R.id.fr3);

        bookmark=new Bookmark();
        explore=new Explore();
        profile=new Profile();
        settings=new Settings();

        nav.setSelectedItemId(R.id.explore);
        setFragment(explore);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.explore :
                        setFragment(explore);

                        return true;

                    case R.id.bookmark:

                        setFragment(bookmark);
                        return true;

                    case R.id.profile:


                        setFragment(profile);
                        return true;

                    case R.id.settings:
                        setFragment(settings);
                        return true;

                    default:
                        return false;


                }
            }
        });
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction frag=getSupportFragmentManager().beginTransaction();
        frag.replace(R.id.fr3,fragment);
        frag.commit();
    }


}