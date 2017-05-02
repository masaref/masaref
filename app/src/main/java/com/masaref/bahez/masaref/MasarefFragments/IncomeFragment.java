package com.masaref.bahez.masaref.MasarefFragments;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomListAdapter;
import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomSpinnerAdapter;
import com.masaref.bahez.masaref.R;

import java.util.ArrayList;

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


    // Content Resolver
    ContentResolver resolver;

    // URI
    static final Uri URI_TABLE_CARD = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCard");
    static final Uri URI_TABLE_CATEGORY = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCategory");


    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create a view
        View inflatedView = inflater.inflate(R.layout.fragment_income, container, false);

        // GUI Spinner
        spinnerOfIncomeCard = (Spinner) inflatedView.findViewById(R.id.spn_fragment_income_card_type);
        spinnerOfIncomeCategory = (Spinner) inflatedView.findViewById(R.id.spn_fragment_income_category);

        // Initialized resolver
        resolver = getContext().getContentResolver();


        setViewForSpinnerCard();

        setViewForSpinnerIncomeCategory();


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



    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    class CustomListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(), "Something selected", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }



}
