package com.masaref.bahez.masaref.MasarefActivities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider;
import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomSpinnerAdapter;
import com.masaref.bahez.masaref.R;

/**
 * Class Name: HomeActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 1.30.2017
 * Purpose: HomeActivity class is created to implement Home page of Masaref.
 * Additionally, this class uses Fragments to display different parts of the Masaref.
 */

public class HomeActivity extends GeneralActivity {

    //GUI Components
    Spinner spinnerListOfCards;

    // GUI Card Balance
    TextView cardBalanceIncome;
    TextView cardBalanceExpense;
    TextView cardBalanceBalance;

    // income based on card
    double incomeBasedOnCard=0.0;

    double expenseBasedOnCard=0.0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GeneralActivity's method to handle the navigation drawer.
        super.generateDrawer();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openOperationsActivity = new Intent(HomeActivity.this, OperationsActivity.class);
                startActivity(openOperationsActivity);
            }
        });

        // GUI Resolve
        spinnerListOfCards = (Spinner) findViewById(R.id.spinner_home_activity_display_card);
        spinnerListOfCards.setOnItemSelectedListener(new ListenerForCardSpinner());

        cardBalanceIncome = (TextView) findViewById(R.id.txt_home_activity_display_income_value);
        cardBalanceExpense = (TextView) findViewById(R.id.txt_home_activity_display_expense_value);
        cardBalanceBalance = (TextView) findViewById(R.id.txt_home_activity_display_balance_value);


        // Setting up Home View
        setViewForSpinnerCard();

        setViewForCardBalanceIncome();

        setViewForCardBalanceExpense();

        setViewForCardBalanceBalance();



    }

    @Override
    public void onResume(){
        super.onResume();
        setViewForCardBalanceIncome();
        setViewForCardBalanceExpense();
        setViewForCardBalanceBalance();

    }

    public void setViewForSpinnerCard(){

        // Get Card Value from DB.
        ContentResolver resolver = getContentResolver();
        int size, counter = 0;
        String cardItemsName []={};
        Integer cardItemsIcon []={};

        try{
            String [] projectionFromCard = {"card_name" , "card_icon"};
            Cursor cursor = resolver.query(MasarefContentProvider.URI_CARD, projectionFromCard, null, null, null);



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
            Toast.makeText(this, "Database Record Missing" , Toast.LENGTH_SHORT).show();
        }

        // Create adapter for Card types
        CustomSpinnerAdapter adapterForCardType = new CustomSpinnerAdapter(this, cardItemsName, cardItemsIcon);
        spinnerListOfCards.setAdapter(adapterForCardType);
        adapterForCardType.notifyDataSetChanged();

    }


    public void setViewForCardBalanceIncome(){
        int getSelectedCardID = spinnerListOfCards.getSelectedItemPosition();
        double income = updateIncomeBasedOnCard(getSelectedCardID);
        cardBalanceIncome.setText(income +"");

    }


    public void setViewForCardBalanceExpense() {

        int getSelectedCardID = spinnerListOfCards.getSelectedItemPosition();
        double expense = updateExpenseBasedOnCard(getSelectedCardID);
        cardBalanceExpense.setText(expense +"");
    }

    public void setViewForCardBalanceBalance(){
        int getSelectedCardID = spinnerListOfCards.getSelectedItemPosition();
        double balance = updateBalanceBasedOnCard(getSelectedCardID);
        cardBalanceBalance.setText(balance +"");
    }


    public double updateBalanceBasedOnCard(int cardID){
        double tempExpense, tempIncome = 0.0;
        tempExpense = updateExpenseBasedOnCard(cardID);
        tempIncome = updateIncomeBasedOnCard(cardID);
        return (tempIncome-tempExpense);

    }




    public double updateIncomeBasedOnCard(int cardID){

        ContentResolver incomeResolver = getContentResolver();

        try{
            String [] projectionFromCard = {"income_amount" , "card_id"};
            Cursor cursor = incomeResolver.query(MasarefContentProvider.URI_INCOME, projectionFromCard, "card_id  = ? ", new String[]{String.valueOf(cardID) }, null);
            incomeBasedOnCard=0.0;


            if(cursor.moveToFirst()) {
                do {
                    incomeBasedOnCard = incomeBasedOnCard +  cursor.getDouble(cursor.getColumnIndex("income_amount"));
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(this, "Database Record Missing" , Toast.LENGTH_SHORT).show();
        }

        return incomeBasedOnCard;

    }


    public double updateExpenseBasedOnCard(int cardID){

        ContentResolver expenseResolver = getContentResolver();

        try{
            String [] projectionFromCard = {"expense_amount" , "card_id"};
            Cursor cursor = expenseResolver.query(MasarefContentProvider.URI_EXPENSE, projectionFromCard, "card_id  = ? ", new String[]{String.valueOf(cardID) }, null);
            expenseBasedOnCard=0.0;


            if(cursor.moveToFirst()) {
                do {
                    expenseBasedOnCard = expenseBasedOnCard +  cursor.getDouble(cursor.getColumnIndex("expense_amount"));
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(this, "Database Record Missing" , Toast.LENGTH_SHORT).show();
        }

        return expenseBasedOnCard;


    }
    class ListenerForCardSpinner implements Spinner.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setViewForCardBalanceIncome();
            setViewForCardBalanceExpense();
            setViewForCardBalanceBalance();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }



}
