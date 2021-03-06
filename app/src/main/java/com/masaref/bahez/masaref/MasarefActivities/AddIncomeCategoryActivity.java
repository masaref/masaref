package com.masaref.bahez.masaref.MasarefActivities;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomListAdapter;
import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomSpinnerAdapter;
import com.masaref.bahez.masaref.R;

public class AddIncomeCategoryActivity extends AppCompatActivity {

    // GUI References
    // List view
    ListView defaultIncomeCategories;

    // Content Resolver
    ContentResolver resolver;

    // URI Category
    final Uri URI_TABLE_CATEGORY = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCategory");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initializing GUI
        defaultIncomeCategories = (ListView) findViewById(R.id.add_income_category_default_category_list);

        // getDefault Income Categories
        getDefaultIncomeCategories();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_income_category_action_bar_menu, menu);
        return true;
    }

    public void getDefaultIncomeCategories() {
        // Get Income Category Value from DB.
        int size, counter = 0;
        String incomeCategoryItem []={};
        Integer incomeItemsIcon []={};
        resolver = getContentResolver();

        try{
            String [] projectionFromCategory = {"category_name" , "category_icon", "category_type"};
            Cursor cursor = resolver.query(URI_TABLE_CATEGORY, projectionFromCategory, "category_type  = ? ", new String[]{"income"} , null);



            if(cursor.moveToFirst()) {
                size = cursor.getCount();
                incomeCategoryItem = new String[size];
                incomeItemsIcon = new Integer[size];

                do {
                    incomeCategoryItem[counter] = cursor.getString(cursor.getColumnIndex("category_name"));
                    incomeItemsIcon[counter] = cursor.getInt(cursor.getColumnIndex("category_icon"));
                    counter++;
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(this, "Database Record Missing" + e.getMessage() , Toast.LENGTH_SHORT).show();
        }
        // Creating adapter for Income category
        CustomListAdapter adapterForIncomeType = new CustomListAdapter(this, incomeCategoryItem, incomeItemsIcon);
        defaultIncomeCategories.setAdapter(adapterForIncomeType);

        adapterForIncomeType.notifyDataSetChanged();
    }


}
