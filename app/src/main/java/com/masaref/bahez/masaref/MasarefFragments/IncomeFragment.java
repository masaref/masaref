package com.masaref.bahez.masaref.MasarefFragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider;
import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomSpinnerAdapter;
import com.masaref.bahez.masaref.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Fragment Name: IncomeFragment
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: IncomeFragment provides users the Income category and user can debit to an account.
 * Additionally, this Fragment is used by OperationsActivity.
 */

public class IncomeFragment extends Fragment {

    // GUI References
    // Spinners
    Spinner spinnerOfIncomeCard;
    Spinner spinnerOfIncomeCategory;



    EditText incomeDate;
    EditText incomeTime;
    EditText incomeAmount;
    EditText incomeDescription;


    // Calender
    Calendar calendar;

    // Content Resolver
    ContentResolver resolver;

    // URI
    static final Uri URI_TABLE_CARD = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCard");
    static final Uri URI_TABLE_CATEGORY = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCategory");
    static final Uri URI_TABLE_GLOBAL_CONFIGURATION = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableGlobalConfiguration");


    double inputAmount = 0.0 ;
    String inputDate = "";
    String inputTime = "";
    String inputDescription = "";
    int inputCardId;
    int inputCategoryId;


    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Create a view
        View inflatedView = inflater.inflate(R.layout.fragment_income, container, false);

        // GUI Spinner
        spinnerOfIncomeCard = (Spinner) inflatedView.findViewById(R.id.spn_fragment_income_card_type);
        spinnerOfIncomeCategory = (Spinner) inflatedView.findViewById(R.id.spn_fragment_income_category);

        // GUI EditText
        incomeTime = (EditText) inflatedView.findViewById(R.id.edit_income_fragment_income_time);
        incomeDate = (EditText) inflatedView.findViewById(R.id.edit_income_fragment_income_date);
        incomeAmount = (EditText) inflatedView.findViewById(R.id.edit_fragment_income_input_amount);
        incomeDescription = (EditText) inflatedView.findViewById(R.id.edit_expense_fragment_expense_description);


        // Initialized resolver
        resolver = getContext().getContentResolver();

        // Set View for Spinners
        setViewForSpinnerCard();
        setViewForSpinnerIncomeCategory();

        setViewForDateAndTime();

        // Inflate the layout for this fragment
        return inflatedView;

    }


    public void setViewForSpinnerIncomeCategory(){
        // Get Income Category Value from DB.
        int size, counter = 0;
        String incomeCategoryItem []={};
        Integer incomeItemsIcon []={};

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
            Toast.makeText(getContext(), "Database Record Missing" + e.getMessage() , Toast.LENGTH_SHORT).show();
        }
        // Creating adapter for Income category
        CustomSpinnerAdapter adapterForIncomeType = new CustomSpinnerAdapter(getActivity(), incomeCategoryItem, incomeItemsIcon);
        spinnerOfIncomeCategory.setAdapter(adapterForIncomeType);

        adapterForIncomeType.notifyDataSetChanged();
    }


    public void setViewForSpinnerCard(){

        // Get Card Value from DB.
        int size, counter = 0;
        String cardItemsName []={};
        Integer cardItemsIcon []={};

        try{
            String [] projectionFromCard = {"card_name" , "card_icon"};
            Cursor cursor = resolver.query(URI_TABLE_CARD, projectionFromCard, null, null, null);



            if(cursor.moveToFirst()) {
                size = cursor.getCount();
                cardItemsName = new String[size];
                cardItemsIcon = new Integer[size];

                do {
                    cardItemsName[counter] = cursor.getString(cursor.getColumnIndex("card_name"));
                    cardItemsIcon[counter] = cursor.getInt(cursor.getColumnIndex("card_icon"));
                    counter++;
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Database Record Missing" , Toast.LENGTH_SHORT).show();
        }

        // Create adapter for Card types
        CustomSpinnerAdapter adapterForCardType = new CustomSpinnerAdapter(getActivity(), cardItemsName, cardItemsIcon);
        spinnerOfIncomeCard.setAdapter(adapterForCardType);

        adapterForCardType.notifyDataSetChanged();


    }

    public void setViewForDateAndTime(){

        // Get date and time format from db.
        String defaultDateFormat = "";
        String defaultTimeFormat = "";

        resolver = getActivity().getContentResolver();

        try{
            String [] projectionFromCategory = {"conf_name" , "conf_value",};
            Cursor cursor = resolver.query(URI_TABLE_GLOBAL_CONFIGURATION, projectionFromCategory, null,null, null);



            if(cursor.moveToFirst()) {

                do {
                    if( cursor.getString(cursor.getColumnIndex("conf_name")).equals("dateformat") )
                        defaultDateFormat = cursor.getString(cursor.getColumnIndex("conf_value"));
                    else if ( cursor.getString(cursor.getColumnIndex("conf_name")).equals("timeformat") ){
                        defaultTimeFormat = cursor.getString(cursor.getColumnIndex("conf_value"));
                    }
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(getActivity(), "Database Record Missing" + e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        calendar = Calendar.getInstance();


        SimpleDateFormat simpleDate= null;
        SimpleDateFormat simpleTime= null;

        switch (defaultDateFormat){
            case "dd-mm-yyyy":
                simpleDate = new SimpleDateFormat("dd-MM-yyyy");
                break;
            case "mm-dd-yyyy":
                simpleDate  = new SimpleDateFormat("MM-dd-yyyy");
                break;
            case "yyyy-mm-dd":
                simpleDate  = new SimpleDateFormat("yyyy-MM-dd");
                break;
        }

        switch (defaultTimeFormat){
            case "12:00":
                simpleTime = new SimpleDateFormat("hh:mm:a");
                break;
            case "24:00":
                simpleTime = new SimpleDateFormat("HH:mm");
                break;
        }

        String date = simpleDate.format(calendar.getTime());
        String time = simpleTime.format(calendar.getTime());


        incomeDate.setText(date);
        incomeTime.setText(time);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_income_fragment_action_bar_menu, menu);
    }


    public boolean insertRecord(){

        inputAmount = Double.parseDouble(incomeAmount.getText().toString());

        inputDate = incomeDate.getText().toString();

        inputTime = incomeTime.getText().toString();

        inputDescription = incomeDescription.getText().toString();

        inputCardId = spinnerOfIncomeCard.getSelectedItemPosition();

        inputCategoryId = spinnerOfIncomeCategory.getSelectedItemPosition();



        ContentValues values = new ContentValues();
        values.put(MasarefContentProvider.TABLE_INCOME_FIELD_AMOUNT, inputAmount);
        values.put(MasarefContentProvider.TABLE_INCOME_FIELD_DATE, inputDate);
        values.put(MasarefContentProvider.TABLE_INCOME_FIELD_TIME, inputTime);
        values.put(MasarefContentProvider.TABLE_INCOME_FIELD_DESCRIPTION, inputDescription);
        values.put(MasarefContentProvider.TABLE_INCOME_FIELD_CARD_REF, inputCardId);
        values.put(MasarefContentProvider.TABLE_INCOME_FIELD_CATEGORY_REF, inputCategoryId);
        Uri uri = getActivity().getContentResolver().insert(MasarefContentProvider.URI_INCOME, values);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.add_income_fragment_action_bar_create:
                insertRecord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}

