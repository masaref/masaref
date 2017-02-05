package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: SettingsActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: SettingsActivity class is created to provided access settings of Masaref.
 * Additionally, this class uses Fragments or other Activities to display settings.
 */

public class SettingsActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GeneralActivity's method to handle the navigation drawer.
        super.generateDrawer();

    }

    // Defines customized navigation through the application.
    public void onBackPressed(){
        Intent openHome = new Intent(this, HomeActivity.class);
        startActivity(openHome);
        finish();
    }

}
