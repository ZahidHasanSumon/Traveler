package com.example.traveller.StartUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.widget.Toast;

import com.example.traveller.R;

public class SplashScreen extends AppCompatActivity {
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(Build.VERSION.SDK_INT>=21){
            window=SplashScreen.this.getWindow();
            window.setStatusBarColor(SplashScreen.this.getResources().getColor(R.color.white));
        }


        final Handler handler = new Handler(Looper.myLooper());








        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,SignUpActivity.class));
                finish();




            }
        },1000);


    }
}
