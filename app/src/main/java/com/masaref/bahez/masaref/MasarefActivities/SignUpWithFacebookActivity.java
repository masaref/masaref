package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: SignUpWithFacebookAccount
 * Created by: Mohammad Rafi Bahez
 * Last Edited:02.05.2017
 * Purpose: SignUpWithFacebookAccount is responsible for creating account for users through FB.
 */


public class SignUpWithFacebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_facebook);
    }

    // Defines customized navigation through the application.
    public void onBackPressed(){
        Intent openWelcome = new Intent(this, WelcomeActivity.class);
        startActivity(openWelcome);
        finish();
    }

}
