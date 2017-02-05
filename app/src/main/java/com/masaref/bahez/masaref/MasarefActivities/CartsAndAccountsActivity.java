package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: CartsAndAccountsActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: CartsAndAccountsActivity class is created to implement Cards and Accounts page of Masaref.
 * Additionally, this class uses Fragments to display different parts of the Agenda.
 */

public class CartsAndAccountsActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts_and_accounts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GeneralActivity's method to handle the navigation drawer.
        super.generateDrawer();
    }

    public void onBackPressed(){
        Intent openHome = new Intent(this, HomeActivity.class);
        startActivity(openHome);
        finish();
    }

}
