package com.masaref.bahez.masaref.MasarefDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.masaref.bahez.masaref.MasarefModel.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherdill Noori on 2017-02-10.
 */

public class MasarefDatabaseAdapter {
    //You can put all your CRUD operations inside this class

    MasarefDatabaseHelper dbHelper;
    public MasarefDatabaseAdapter(Context context){
        dbHelper=new MasarefDatabaseHelper(context);

    }


    //Card CRUD operations

    public List<Card> ReadAllCards(){
        List<Card> cards=new ArrayList<Card>();
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        String[] columns={"id","card_name","card_amount","card_current_amount"};
        Cursor cursor=database.query(dbHelper.TABLE_CARDS,columns,null,null,null,null,null);
        int indexId=cursor.getColumnIndex("id");
        int index_card_name=cursor.getColumnIndex("card_name");
        int index_card_amount=cursor.getColumnIndex("card_amount");
        int index_card_current_amount=cursor.getColumnIndex("card_current_amount");

        while(cursor.moveToNext()){
            Card card=new Card(cursor.getInt(indexId),cursor.getString(index_card_name),
                    cursor.getDouble(index_card_amount),cursor.getDouble(index_card_current_amount));
            cards.add(card);



        }

        return cards;
    }


    public boolean CreateCard(String cardName,double card_total_amount){
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbHelper.CARD_NAME,cardName);
        contentValues.put(dbHelper.CARD_TOTAL_AMOUNT,card_total_amount);
        contentValues.put(dbHelper.CARD_CURRENT_AMOUNT,card_total_amount);
        long result=database.insert(dbHelper.TABLE_CARDS,null,contentValues);
        if(result>=1){
            return true;
        }

        return false;

    }


    public boolean DeleteCard(String cardName){
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        long id= database.delete(dbHelper.TABLE_CARDS,dbHelper.CARD_NAME+"=?",new String[]{cardName});
        if(id>=1){
            return true;
        }

        return false;

    }



    //Expense Table Insertion Method

    public boolean insert_Expense(double amount, String description,String date, int category_id, int card_id,String icon_address){
        SQLiteDatabase expense_database=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbHelper.EXPENSE_AMOUNT,amount);
        contentValues.put(dbHelper.EXPENSE_DESCRIPTION,description);
        contentValues.put(dbHelper.EXPENSE_DATE,date);
        contentValues.put(dbHelper.EXPENSE_CATEGORY_ID,category_id);
        contentValues.put(dbHelper.EXPENSE_CARD_ID,card_id);
        contentValues.put(dbHelper.EXPENSE_ICON_ADDRESS,icon_address);
        long result_expense=expense_database.insert(dbHelper.TABLE_EXPENSES,null,contentValues);
        if(result_expense !=-1){
            return true;
        }else{
            return false;
        }
    }




    /*We make our sqlite OpenHelper  class static to ensure that
    there is always only one instance of this class in our entire application */
    static  class MasarefDatabaseHelper extends SQLiteOpenHelper {
        // Masaref App Database Name
        private static final String DATABASE_NAME = "Masaref";
        // Database Version
        private static final int DATABASE_VERSION = 2;

        // All tables names
        private static final String TABLE_INCOMES = "incomes";
        private static final String TABLE_EXPENSES = "expenses";
        private static final String TABLE_AGENDAS = "agendas";
        private static final String TABLE_CARDS="cards";
        private static final String TABLE_USERS="users";
        private static final String TABLE_CATEGORIES="categories";




        //ALL tables columns start

        //CATEGORY Table- column names
        private static final String CATEGORY_ID="id";
        private static final String CATEGORY_NAME="category_name";

        // EXPENSES Table - column names
        private static final String EXPENSE_ID = "id";
        private static final String EXPENSE_AMOUNT = "expense_amount";
        private static final String EXPENSE_DESCRIPTION="expense_description";
        private static final String EXPENSE_DATE="expense_date";
        private static final String EXPENSE_CATEGORY_ID="expense_category_id";
        private static final String EXPENSE_ICON_ADDRESS="ICON_ADDRESS";
        private static final String EXPENSE_CARD_ID="expense_card_id";

        // INCOMES Table - column names
        private static final String INCOME_ID = "id";
        private static final String INCOME_AMOUNT = "income_amount";
        private static final String INCOME_DESCRIPTION="income_description";
        private static final String INCOME_DATE="income_date";
        private static final String INCOME_ICON_ADDRESS="ICON_ADDRESS";
        private static final String INCOME_CATEGORY_ID="income_category_id";
        private static final String INCOME_CARD_ID="income_card_id";

        //CARDS Table- column names
        private static final String CARD_ID="id";
        private static final String CARD_NAME="card_name";
        private static final String CARD_TOTAL_AMOUNT="card_amount";
        private static final String CARD_CURRENT_AMOUNT="card_current_amount";

        //AGENDAS Table- column names
        private static final String AGENDA_ID="id";
        private static final String  AGENDA_NAME="agenda_name";
        private static final String  AGENDA_DATE="agenda_date";
        private static final String AGENDA_REMINDER="agenda_reminder";

        // USERS Table - column names
        private static final String USER_ID = "id";
        private static final String USER_FIRST_NAME="first_name";
        private static final String USER_LAST_NAME="last_name";
        private static final String USER_USER_NAME="username";
        private static final String USER_PASSWORD="password";
        private static final String USER_EMAIL_ID="email_address";
        private static final String USER_FIRST_QUESTION="first_question";
        private static final String USER_FIRST_QUESTION_ANSWER="answer_first_question";
        private static final String USER_SECOND_QUESTION="second_question";
        private static final String USER_SECOND_QUESTION_ANSWER="answer_second_question";
        private static final String USER_MOBILE_NUMBER="mobile_number";
        private static final String USER_DATE_OF_BIRTH="date_of_birth";
        private static final String USER_COUNTRY_NAME="country";
        private static final String USER_ORGANIZATION_NAME="organization";
        private static final String USER_OCCUPATION="occupation";

        //ALL tables columns end



        // All tables creation statements start

        // CATEGORY table create statement
        private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES
                + "("
                + CATEGORY_ID + "       INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CATEGORY_NAME + "     VARCHAR(100) UNIQUE NOT NULL"
                +")";

        private static final String CREATE_TABLE_INCOMES = "CREATE TABLE " + TABLE_INCOMES
                + "("
                + INCOME_ID + "             INTEGER     PRIMARY KEY     AUTOINCREMENT,"
                + INCOME_AMOUNT + "         REAL        NOT NULL,"
                + INCOME_DATE   +"          DATETIME    NOT NULL,"
                + INCOME_ICON_ADDRESS +"    TEXT NOT NULL,"
                + INCOME_DESCRIPTION + "    TEXT ,"
                + INCOME_CATEGORY_ID + "    INTEGER     NOT NULL,"
                + INCOME_CARD_ID     + "    INTEGER     NOT NULL"
                + ")";

        private static final String CREATE_TABLE_EXPENSES = "CREATE TABLE " + TABLE_EXPENSES
                + "("
                + EXPENSE_ID + "            INTEGER     PRIMARY KEY     AUTOINCREMENT,"
                + EXPENSE_AMOUNT + "        REAL        NOT NULL,"
                + EXPENSE_DATE   +"         DATETIME    NOT NULL,"
                + EXPENSE_DESCRIPTION + "   TEXT ,"
                +EXPENSE_ICON_ADDRESS +"    TEXT NOT NULL,"
                + EXPENSE_CATEGORY_ID + "   INTEGER     NOT NULL,"
                + EXPENSE_CARD_ID     + "   INTEGER     NOT NULL"
                + ")";

        private static final String CREATE_TABLE_CARDS = "CREATE TABLE " + TABLE_CARDS
                + "("
                + CARD_ID + "               INTEGER         PRIMARY KEY     AUTOINCREMENT,"
                + CARD_NAME + "             VARCHAR(100)    NOT NULL,"
                + CARD_TOTAL_AMOUNT   +"    REAL            NOT NULL,"
                + CARD_CURRENT_AMOUNT +"    REAL            NOT NULL"
                + ")";

        private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS
                + "("
                + USER_ID + "               INTEGER           PRIMARY KEY     AUTOINCREMENT,"
                + USER_FIRST_NAME + "       VARCHAR(100)      NOT NULL,"
                + USER_LAST_NAME   +"       VARCHAR(100)      NOT NULL,"
                + USER_EMAIL_ID + "         VARCHAR(100)      NOT NULL UNIQUE,"
                + USER_USER_NAME + "         VARCHAR(100)      NOT NULL,"
                + USER_PASSWORD + "         VARCHAR(255)      NOT NULL,"
                + USER_MOBILE_NUMBER+ "     VARCHAR(50) NOT NULL,"
                + USER_COUNTRY_NAME+"       VARCHAR(150),"
                + USER_OCCUPATION+"         VARCHAR(150),"
                + USER_ORGANIZATION_NAME+"  TEXT,"
                + USER_DATE_OF_BIRTH + "     DATE,"
                + USER_FIRST_QUESTION +"    TEXT NOT NULL, "
                + USER_FIRST_QUESTION_ANSWER+" VARCHAR(200) NOT NULL,"
                + USER_SECOND_QUESTION + "    TEXT NOT NULL,"
                + USER_SECOND_QUESTION_ANSWER + " VARCHAR(200) NOT NULL"
                + ")";
        private static final String CREATE_TABLE_AGENDAS = "CREATE TABLE " + TABLE_AGENDAS
                + "("
                + AGENDA_ID + "             INTEGER     PRIMARY KEY     AUTOINCREMENT,"
                + AGENDA_NAME + "           VARCHAR(255)        NOT NULL,"
                + AGENDA_DATE   +"          DATETIME    NOT NULL,"
                + AGENDA_REMINDER + "       TINYINT "
                + ")";


        //All tables creation statements end

        public MasarefDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // creating required tables
            db.execSQL(CREATE_TABLE_CATEGORIES);
            db.execSQL(CREATE_TABLE_INCOMES);
            db.execSQL(CREATE_TABLE_EXPENSES);
            db.execSQL(CREATE_TABLE_CARDS);
            db.execSQL(CREATE_TABLE_AGENDAS);
            db.execSQL(CREATE_TABLE_USERS);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOMES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDAS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

            // recreate new tables
            onCreate(db);
        }
    }

}
