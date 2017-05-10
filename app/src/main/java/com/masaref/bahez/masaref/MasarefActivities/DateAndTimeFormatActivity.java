package com.masaref.bahez.masaref.MasarefActivities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider;
import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomListAdapter;
import com.masaref.bahez.masaref.R;

public class DateAndTimeFormatActivity extends AppCompatActivity {

    String dateFormat;
    String timeFormat;
    String defaultDateFormat="";
    String defaultTimeFormat="";

    // GUI Reference
    RadioGroup dateSelection;
    RadioGroup timeSelection;

    RadioButton dayFirst;
    RadioButton dayMiddle;
    RadioButton dayLast;
    RadioButton twelve;
    RadioButton twentyFour;


    // Resolver
    ContentResolver resolver;

    // URI Category
    final Uri URI_TABLE_GLOBAL_CONFIGURATION = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableGlobalConfiguration");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time_format);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Resolve GUI Reference
        dateSelection = (RadioGroup) findViewById(R.id.rgroup_date_and_time_format_date);
        timeSelection = (RadioGroup) findViewById(R.id.rgroup_date_and_time_format_time);

        dayFirst = (RadioButton) findViewById(R.id.rButton_date_and_time_format_dd_mm_yyyy);
        dayMiddle = (RadioButton) findViewById(R.id.rButton_date_and_time_format_mm_dd_yyyy);
        dayLast = (RadioButton) findViewById(R.id.rButton_date_and_time_format_yyyy_mm_dd);
        twelve = (RadioButton) findViewById(R.id.rButton_date_and_time_format_12_00);
        twentyFour = (RadioButton) findViewById(R.id.rButton_date_and_time_format_24_00);


        // Register Listener
        dateSelection.setOnCheckedChangeListener(new listener() );
        timeSelection.setOnCheckedChangeListener(new listener() );

        // Select the default option
        getDefaultDateAndTimeOption();

    }


    public void getDefaultDateAndTimeOption(){
        // Get Income Category Value from DB.
        resolver = getContentResolver();

        try{
            String [] projectionFromCategory = {"conf_name" , "conf_value",};
            Cursor cursor = resolver.query(URI_TABLE_GLOBAL_CONFIGURATION, projectionFromCategory, null,null, null);



            if(cursor.moveToFirst()) {

                do {
                    if( cursor.getString(cursor.getColumnIndex("conf_name")).equals("dateformat") )
                        this.defaultDateFormat = cursor.getString(cursor.getColumnIndex("conf_value"));
                    else if ( cursor.getString(cursor.getColumnIndex("conf_name")).equals("timeformat") ){
                        this.defaultTimeFormat = cursor.getString(cursor.getColumnIndex("conf_value"));
                    }
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(this, "Database Record Missing" + e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        switch (defaultDateFormat){
            case "dd-mm-yyyy":
                dayFirst.setChecked(true);
                break;
            case "mm-dd-yyyy":
                dayMiddle.setChecked(true);
                break;
            case "yyyy-mm-dd":
                dayLast.setChecked(true);
                break;
        }

        switch (defaultTimeFormat){
            case "12:00":
                twelve.setChecked(true);
                break;
            case "24:00":
                twentyFour.setChecked(true);
                break;
        }
    }



    class listener implements RadioGroup.OnCheckedChangeListener{
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (group.getId() == dateSelection.getId()) {
                switch (checkedId) {
                    case R.id.rButton_date_and_time_format_dd_mm_yyyy:
                        dateFormat = dayFirst.getText().toString();
                        break;
                    case R.id.rButton_date_and_time_format_mm_dd_yyyy:
                        dateFormat = dayMiddle.getText().toString();
                        break;
                    case R.id.rButton_date_and_time_format_yyyy_mm_dd:
                        dateFormat = dayLast.getText().toString();
                        break;
                }

                values.put(MasarefContentProvider.TABLE_GLOBAL_CONFIGURATION_FIELD_VALUE, dateFormat);
                int i = resolver.update(URI_TABLE_GLOBAL_CONFIGURATION, values, "conf_name = ?", new String[]{"dateformat"});

            } else if (group.getId() == timeSelection.getId()) {
                switch (checkedId) {
                    case R.id.rButton_date_and_time_format_12_00:
                        timeFormat = twelve.getText().toString();
                        break;
                    case R.id.rButton_date_and_time_format_24_00:
                        timeFormat = twentyFour.getText().toString();
                        break;
                }
                values.put(MasarefContentProvider.TABLE_GLOBAL_CONFIGURATION_FIELD_VALUE, timeFormat);
                int j = resolver.update(URI_TABLE_GLOBAL_CONFIGURATION, values, "conf_name = ?", new String[]{"timeformat"});
            }
        }

    }

    // Defines customized navigation through the application.
    public void onBackPressed(){
        Intent openHome = new Intent(this, SettingsActivity.class);
        startActivity(openHome);
        finish();
    }

}
