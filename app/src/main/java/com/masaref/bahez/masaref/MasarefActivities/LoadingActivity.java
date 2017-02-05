package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: LoadingActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: LoadingActivity class is used to display logo and loading page.
 * Additionally, this class uses broadcast receiver and services to load all the desired components of the Masaref.
 */

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(LoadingActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }

}
