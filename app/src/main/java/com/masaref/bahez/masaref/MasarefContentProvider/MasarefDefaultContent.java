package com.masaref.bahez.masaref.MasarefContentProvider;


import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.masaref.bahez.masaref.R;
/**
 * Created by Bahez on 4/26/2017.
 */
public class MasarefDefaultContent extends Service {



    // Resolver Part.
    public static final Uri URI_TABLE_INCOME = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableIncome");

    public static final Uri URI_TABLE_EXPENSE = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableExpense");

    public static final Uri URI_TABLE_AGENDA = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableAgenda");

    static final Uri URI_TABLE_CARD = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCard");

    public static final Uri URI_TABLE_CATEGORY = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCategory");

    public static final Uri URI_TABLE_USER = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableUser");


    ContentResolver resolver;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        resolver = getContentResolver();
        insertDefaultCards();
        insertDefaultCategoryIncome();
        insertDefaultCategoryExpense();
        return START_STICKY;
    }

    // ********** Inserting default cards *******************
    public void insertDefaultCards(){

        ContentValues values = new ContentValues();

        String cardName [] = {"Cash", "Card 1", "Card 2", "Savings"};
        Double cardAmounts [] = {0.0, 0.0, 0.0, 0.0 };
        Integer cardIcons [] = {R.drawable.ic_card_card_1, R.drawable.ic_card_card_1, R.drawable.ic_card_card_1, R.drawable.ic_card_saving };

        for (int i = 0; i <4; i++){
            values.put(MasarefContentProvider.TABLE_CARD_FIELD_NAME, cardName[i] );
            values.put(MasarefContentProvider.TABLE_CARD_FIELD_CURRENT_AMOUNT, cardAmounts[i] );
            values.put(MasarefContentProvider.TABLE_CARD_FIELD_TOTAL_AMOUNT, cardAmounts[i] );
            values.put(MasarefContentProvider.TABLE_CARD_FIELD_ICON, cardIcons[i] );
            Uri uri = getContentResolver().insert(MasarefContentProvider.URI_CARD, values);
        }
    }


    // ********** Inserting default cards *******************
    public void insertDefaultCategoryIncome(){
        ContentValues values = new ContentValues();

        String categoryIncomeName [] = {"Loan", "Salary", "Benefits"};
        String type= "income";
        Integer categoryIncomeIcon  [] = {R.drawable.ic_income_loan, R.drawable.ic_income_salary, R.drawable.ic_income_benefits};

        for(int i=0; i <categoryIncomeName.length; i++){
            values.put(MasarefContentProvider.TABLE_CATEGORY_FIELD_NAME, categoryIncomeName[i]);
            values.put(MasarefContentProvider. TABLE_CATEGORY_FIELD_TYPE, type);
            values.put(MasarefContentProvider.TABLE_CATEGORY_FIELD_ICON, categoryIncomeIcon[i]);

            Uri uri = getContentResolver().insert(MasarefContentProvider.URI_CATEGORY, values);
        }
    }

    public void insertDefaultCategoryExpense(){
        ContentValues values = new ContentValues();

        String categoryExpenseName [] = {"Cab" , " Cinema", "Food" , " Fuel", "Gift", "Grocery shopping", "Hotel", "Mobile top up", "Plane ticket" , "Others"};
        String type= "expense";
        Integer categoryIncomeIcon  [] = {R.drawable.ic_expense_cab, R.drawable.ic_expense_cinema, R.drawable.ic_expense_food,
                R.drawable.ic_expense_fuel, R.drawable.ic_expense_gift, R.drawable.ic_expense_grocery_shopping,
                R.drawable.ic_expense_hotel, R.drawable.ic_expense_mobile_credit, R.drawable.ic_expense_plane_ticket, R.drawable.ic_expense_others};


        for(int i=0; i <categoryExpenseName.length; i++){
            values.put(MasarefContentProvider.TABLE_CATEGORY_FIELD_NAME, categoryExpenseName[i]);
            values.put(MasarefContentProvider. TABLE_CATEGORY_FIELD_TYPE, type);
            values.put(MasarefContentProvider.TABLE_CATEGORY_FIELD_ICON, categoryIncomeIcon[i]);

            Uri uri = getContentResolver().insert(MasarefContentProvider.URI_CATEGORY, values);
        }
    }



}
