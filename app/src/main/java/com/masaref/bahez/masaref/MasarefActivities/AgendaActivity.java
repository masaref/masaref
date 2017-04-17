package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: AgendaActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: AgendaActivity class is created to implement Agenda page of Masaref.
 * Additionally, this class uses Fragments to display different parts of the Agenda.
 */

public class AgendaActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GeneralActivity's method to handle the navigation drawer.
        super.generateDrawer();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        
    }

    public void onBackPressed(){
        Intent openHome = new Intent(this, HomeActivity.class);
        startActivity(openHome);
        finish();
    }

}
