package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomListAdapter;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: SettingsActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: SettingsActivity class is created to provided access settings of Masaref.
 * Additionally, this class uses Fragments or other Activities to display settings.
 */

public class SettingsActivity extends GeneralActivity {


    String [] listItems = {"Expense Category" , "Income Category" , "Currency Format", "Date and Time Format",
            "Rate Application", "Terms of Use", " About Us"};
    Integer [] imgItems ={R.drawable.ic_settings_category_expense,
            R.drawable.ic_settings_category_income,
            R.drawable.ic_settings_currency_format,
            R.drawable.ic_settings_date_and_item_format,
            R.drawable.ic_settings_rate_application,
            R.drawable.ic_settings_terms_of_use,
            R.drawable.ic_settings_about_us};
    ListView settingsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GeneralActivity's method to handle the navigation drawer.
        super.generateDrawer();

        // Setting up content for settings
        CustomListAdapter adapter=new CustomListAdapter(this, listItems, imgItems);
        settingsItems=(ListView)findViewById(R.id.settings_activity_list);
        settingsItems.setAdapter(adapter);

        settingsItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent opener;

               switch (position){
                   case 0:
                       opener = new Intent(SettingsActivity.this, AddExpenseCategoryActivity.class);
                       startActivity(opener);
                       break;
                   case 1:
                       opener = new Intent(SettingsActivity.this, AddIncomeCategoryActivity.class);
                       startActivity(opener);
                       break;
                   case 2:
                       break;
                   case 3:
                       break;
                   case 4:
                       break;
                   case 5:
                       break;
                   case 6:
                       break;
                   default:


               }


            }
        });

    }



    // Defines customized navigation through the application.
    public void onBackPressed(){
        Intent openHome = new Intent(this, HomeActivity.class);
        startActivity(openHome);
        finish();
    }

}
