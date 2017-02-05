package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: WelcomeActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: WelcomeActivity class provides welcomes message to the user.
 * Additionally, this class helps users to select their sign up/sign in options.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    // Sign in handler
    public void signInHandler(View view){
        Intent openSignIn = new Intent(this, SignInActivity.class);
        startActivity(openSignIn);
        finish();
    }

    // Sign up handler
    public void signUpHandler(View view){
        Intent openSignUp = new Intent(this, SignUpActivity.class);
        startActivity(openSignUp);
        finish();
    }

    // Sign un with Facebook handler
    public void signUpWithFacebook(View view){
        Intent openSignUnWithFacebook = new Intent(this, SignUpWithFacebookActivity.class);
        startActivity(openSignUnWithFacebook);
        finish();
    }



}
