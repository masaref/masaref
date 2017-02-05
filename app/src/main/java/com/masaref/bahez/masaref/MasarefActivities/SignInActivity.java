package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.masaref.bahez.masaref.R;


/**
 * Class Name: SignInActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: SignInActivity class is implements the process to sign in.
 */

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }

    // MUST BE CHANGED TO IMPLEMENT THE SIGN IN PROCESS
    public void singInHandler(View view){
        Intent openHome = new Intent(this, HomeActivity.class);
        startActivity(openHome);
        finish();
    }

    // Defines customized navigation through the application.
    public void onBackPressed(){
        Intent openWelcome = new Intent(this, WelcomeActivity.class);
        startActivity(openWelcome);
        finish();
    }

    public void needHelp(View view){
        Intent openRecoverAccount = new Intent(this, RecoverAccountActivity.class);
        startActivity(openRecoverAccount);
        finish();
    }
}
