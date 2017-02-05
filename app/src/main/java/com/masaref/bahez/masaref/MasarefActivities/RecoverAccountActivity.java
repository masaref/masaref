package com.masaref.bahez.masaref.MasarefActivities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.masaref.bahez.masaref.MasarefFragments.NewAccountLoginInfoFragment;
import com.masaref.bahez.masaref.MasarefFragments.NewAccountUserInfoFragment;
import com.masaref.bahez.masaref.MasarefFragments.RecoverAccountRegisteredEmail;
import com.masaref.bahez.masaref.MasarefFragments.RecoverAccountRegisteredSecretQuestions;
import com.masaref.bahez.masaref.R;

/**
 * Class Name: RecoverAccountActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: RecoverAccountActivity class is created to display the process to recover forgotten password.
 * This activity provides the process through two Fragments namely, RecoverAccountRegisteredEmail
 * and RecoverAccountRegisteredSecretQuestions
 */

public class RecoverAccountActivity extends AppCompatActivity {
    // GUI References
    Button next;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_account);
        recoverRegisteredEmail();

        // Handle Actions Listener
        next = (Button)findViewById(R.id.btn_recover_account_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoverSecretQuestions();
            }
        });
    }

    public void recoverRegisteredEmail(){

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecoverAccountRegisteredEmail recoveryEmail = new RecoverAccountRegisteredEmail();
        fragmentTransaction.add(R.id.recover_account_activity_container , recoveryEmail, "recoveryEmail");
        fragmentTransaction.commit();

    }


    public void recoverSecretQuestions(){

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecoverAccountRegisteredSecretQuestions secretQuestions = new RecoverAccountRegisteredSecretQuestions();
        fragmentTransaction.replace(R.id.recover_account_activity_container, secretQuestions, "secretQuestions");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Defines customized navigation through the application.
    public void onBackPressed(){
        Intent openWelcome = new Intent(this, WelcomeActivity.class);
        startActivity(openWelcome);
        finish();
    }

}
