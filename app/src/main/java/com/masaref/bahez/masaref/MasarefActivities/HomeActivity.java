package com.masaref.bahez.masaref.MasarefActivities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: HomeActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: HomeActivity class is created to implement Home page of Masaref.
 * Additionally, this class uses Fragments to display different parts of the Masaref.
 */

public class HomeActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GeneralActivity's method to handle the navigation drawer.
        super.generateDrawer();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openOperationsActivity = new Intent(HomeActivity.this, OperationsActivity.class);
                startActivity(openOperationsActivity);
            }
        });
    }
}
