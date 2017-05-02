package com.masaref.bahez.masaref.MasarefFragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomSpinnerAdapter;
import com.masaref.bahez.masaref.R;

/**
 * Fragment Name: ExpenseFragment
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: ExpenseFragment provides users the Expense category and user can withdraw from an account.
 * Additionally, this Fragment is used by OperationsActivity.
 */

public class ExpenseFragment extends Fragment {
    // GUI References
    // Spinners
    Spinner spinnerOfExpenseCard;
    Spinner spinnerOfExpenseCategory;


    // Content Resolver
    ContentResolver resolver;

    // URI
    static final Uri URI_TABLE_CARD = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCard");
    static final Uri URI_TABLE_CATEGORY = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCategory");

    public ExpenseFragment() {
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
        View inflatedView = inflater.inflate(R.layout.fragment_expense, container, false);

        // GUI Spinner
        spinnerOfExpenseCard = (Spinner) inflatedView.findViewById(R.id.spn_fragment_expense_card_type);
        spinnerOfExpenseCategory = (Spinner) inflatedView.findViewById(R.id.spn_fragment_expense_category);

        // Initialized resolver
        resolver = getContext().getContentResolver();


        setViewForSpinnerCard();
        setViewForSpinnerExpenseCategory();


        // Inflate the layout for this fragment
        return inflatedView;
    }


    public void setViewForSpinnerExpenseCategory() {
        // Get Expense Category Value from DB.
        int size, counter = 0;
        String incomeCategoryItem []={};
        Integer incomeItemsIcon []={};

        try{
            String [] projectionFromCategory = {"category_name" , "category_icon", "category_type"};
            Cursor cursor = resolver.query(URI_TABLE_CATEGORY, projectionFromCategory, "category_type  = ? ", new String[]{"expense"} , null);



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
            Toast.makeText(getContext(), "Database Record Missing" +e.getMessage() , Toast.LENGTH_SHORT).show();
        }
        // Creating adapter for Income category
        CustomSpinnerAdapter adapterForExpenseType = new CustomSpinnerAdapter(getActivity(), incomeCategoryItem, incomeItemsIcon);
        spinnerOfExpenseCategory.setAdapter(adapterForExpenseType);

        adapterForExpenseType.notifyDataSetChanged();
        
    }

    public  void setViewForSpinnerCard() {
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
        spinnerOfExpenseCard.setAdapter(adapterForCardType);

        adapterForCardType.notifyDataSetChanged();

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
