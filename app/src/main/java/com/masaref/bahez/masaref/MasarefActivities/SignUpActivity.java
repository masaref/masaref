package com.masaref.bahez.masaref.MasarefActivities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.masaref.bahez.masaref.MasarefFragments.NewAccountLoginInfoFragment;
import com.masaref.bahez.masaref.MasarefFragments.NewAccountUserInfoFragment;
import com.masaref.bahez.masaref.R;

/**
 * Class Name: SignUpActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited:02.05.2017
 * Purpose: SignUpActivity is responsible for creating account for users.
 * Additionally, this class uses two fragments to have user's bio and login information.
 * The Fragments used by this are namely, NewAccountUserInfoFragment, and NewAccountUserLoginInfo.
 */

public class SignUpActivity extends AppCompatActivity {

    // GUI References
    Button next;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userInfo();

        // Handle Actions Listener
        next = (Button)findViewById(R.id.btn_sign_up_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginInfo();
            }
        });

    }

    // Loads the activity responsible to have user's bio information.
    public void userInfo(){

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewAccountUserInfoFragment newUserInfo = new NewAccountUserInfoFragment();
        fragmentTransaction.add(R.id.sign_up_activity_container, newUserInfo, "userinfo");
        fragmentTransaction.commit();

    }


    // Loads the activity responsible to have user's login information.
    public void loginInfo(){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewAccountLoginInfoFragment newUserLoginInfo = new NewAccountLoginInfoFragment();
        fragmentTransaction.replace(R.id.sign_up_activity_container, newUserLoginInfo, "logininfo");
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
