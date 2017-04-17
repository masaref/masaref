package com.masaref.bahez.masaref.MasarefContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;


/**
 * Created by Sherdill Noori on 2017-02-10.
 */

public class MasarefContentProvider  extends ContentProvider {

    static final String PROVIDER_NAME = "com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider";

    // START *********** ALL TABLES ***************//
    private static final String TABLE_INCOME = "income";
    private static final String TABLE_EXPENSE = "expense";
    private static final String TABLE_AGENDA = "agenda";
    private static final String TABLE_CARD="card";
    private static final String TABLE_USER="user";
    private static final String TABLE_CATEGORY="category";
    //--------------END---------------------------//



    // START *********** ALL URLs ***************//
    static final String URL_INCOME = "content://" + PROVIDER_NAME + "/tableIncome";
    static final String URL_EXPENSE = "content://" + PROVIDER_NAME + "/tableExpense";
    static final String URL_AGENDA = "content://" + PROVIDER_NAME + "/tableAgenda";
    static final String URL_CARD = "content://" + PROVIDER_NAME + "/tableCard";
    static final String URL_USER = "content://" + PROVIDER_NAME + "/tableUser";
    static final String URL_CATEGORY = "content://" + PROVIDER_NAME + "/tableCategory";
    //--------------END---------------------------//



    // START *********** ALL URIs ***************//
    public static final Uri URI_INCOME = Uri.parse(URL_INCOME);
    public static final Uri URI_EXPENSE = Uri.parse(URL_EXPENSE);
    public static final Uri URI_AGENDA = Uri.parse(URL_AGENDA);
    public static final Uri URI_CARD = Uri.parse(URL_CARD);
    public static final Uri URI_USER = Uri.parse(URL_USER);
    public static final Uri URI_CATEGORY = Uri.parse(URL_CATEGORY);
    //--------------END---------------------------//


    // START *********** ALL URI CODE ************//
    static final int URI_CODE_INCOME = 10;
    static final int URI_CODE_EXPENSE = 20;
    static final int URI_CODE_AGENDA = 30;
    static final int URI_CODE_CARD = 40;
    static final int URI_CODE_USER = 50;
    static final int URI_CODE_CATEGORY = 60;
    //--------------END---------------------------//


    // START ***********  HASH MAP **************//
    private static HashMap<String, String > values;
    //--------------END---------------------------//


    // START *********** ALL URI MATCHER **********//
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"tableIncome", URI_CODE_INCOME);
        uriMatcher.addURI(PROVIDER_NAME,"tableExpense", URI_CODE_EXPENSE);
        uriMatcher.addURI(PROVIDER_NAME,"tableAgenda", URI_CODE_AGENDA);
        uriMatcher.addURI(PROVIDER_NAME,"tableCard", URI_CODE_CARD);
        uriMatcher.addURI(PROVIDER_NAME,"tableUser", URI_CODE_USER);
        uriMatcher.addURI(PROVIDER_NAME,"tableCategory", URI_CODE_CATEGORY);
    }
    //--------------END---------------------------//


    // START *********** ALL ABOUT DB  ***********//
    private SQLiteDatabase sqlDB;
    static final String DATABASE_NAME = "MasarefDB";
    static final int DATABASE_VERSION = 1;
    //--------------END---------------------------//


    // START *********** TABLES WITH ITS FIELDS **********//
    // INCOME Table - column's name
    static final String TABLE_INCOME_FIELD_ID = "income_id";
    static final String TABLE_INCOME_FIELD_AMOUNT = "income_amount";
    static final String TABLE_INCOME_FIELD_CARD_REF="card_id";
    static final String TABLE_INCOME_FIELD_CATEGORY_REF="category_id";
    static final String TABLE_INCOME_FIELD_DESCRIPTION="income_description";
    static final String TABLE_INCOME_FIELD_DATE="income_date";
    static final String TABLE_INCOME_FIELD_TIME="income_time";
    // INCOME Table - CREATE
    private static final String CREATE_TABLE_INCOME = "CREATE TABLE " + TABLE_INCOME
            + "("
            + TABLE_INCOME_FIELD_ID + "                 INTEGER     PRIMARY KEY     AUTOINCREMENT,"
            + TABLE_INCOME_FIELD_AMOUNT + "             REAL        NOT NULL,"
            + TABLE_INCOME_FIELD_CARD_REF   +"          INTEGER     NOT NULL,"
            + TABLE_INCOME_FIELD_CATEGORY_REF +"        INTEGER     NOT NULL,"
            + TABLE_INCOME_FIELD_DESCRIPTION + "        TEXT         ,"
            + TABLE_INCOME_FIELD_DATE + "               NUMERIC     NOT NULL,"
            + TABLE_INCOME_FIELD_TIME     + "           NUMERIC     NOT NULL"
            + ")";

    // EXPENSE Table - column's name
    static final String TABLE_EXPENSE_FIELD_ID = "expense_id";
    static final String TABLE_EXPENSE_FIELD_AMOUNT = "expense_amount";
    static final String TABLE_EXPENSE_FIELD_CARD_REF="card_id";
    static final String TABLE_EXPENSE_FIELD_CATEGORY_REF="category_id";
    static final String TABLE_EXPENSE_FIELD_DESCRIPTION="expense_description";
    static final String TABLE_EXPENSE_FIELD_DATE="expense_date";
    static final String TABLE_EXPENSE_FIELD_TIME="expense_time";
    // EXPENSE Table - CREATE
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE " + TABLE_EXPENSE
            + "("
            + TABLE_EXPENSE_FIELD_ID + "                INTEGER     PRIMARY KEY     AUTOINCREMENT,"
            + TABLE_EXPENSE_FIELD_AMOUNT + "            REAL        NOT NULL,"
            + TABLE_EXPENSE_FIELD_CARD_REF   +"         INTEGER    NOT NULL,"
            + TABLE_EXPENSE_FIELD_CATEGORY_REF + "      INTEGER ,"
            + TABLE_EXPENSE_FIELD_DESCRIPTION +"        TEXT ,"
            + TABLE_EXPENSE_FIELD_DATE + "              NUMERIC     NOT NULL,"
            + TABLE_EXPENSE_FIELD_TIME     + "          NUMERIC     NOT NULL"
            + ")";

    //CATEGORY Table- column's name
    static final String TABLE_CATEGORY_FIELD_ID="category_id";
    static final String TABLE_CATEGORY_FIELD_NAME="category_name";
    static final String TABLE_CATEGORY_FIELD_TYPE="category_type";
    static final String TABLE_CATEGORY_FIELD_ICON="category_icon";
    // CATEGORY table CREATE
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
            + "("
            + TABLE_CATEGORY_FIELD_ID + "               INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TABLE_CATEGORY_FIELD_NAME + "             TEXT NOT NULL,"
            + TABLE_CATEGORY_FIELD_TYPE + "             TEXT NOT NULL,"
            + TABLE_CATEGORY_FIELD_ICON + "             INTEGER     NOT NULL"
            +")";


    //CARD Table- column's name
    static final String TABLE_CARD_FIELD_ID="card_id";
    static final String TABLE_CARD_FIELD_NAME="card_name";
    static final String TABLE_CARD_FIELD_TOTAL_AMOUNT="card_total_amount";
    static final String TABLE_CARD_FIELD_CURRENT_AMOUNT ="card_current_amount";
    static final String TABLE_CARD_FIELD_ICON="card_icon";
    // CARD table CREATE
    private static final String CREATE_TABLE_CARD = "CREATE TABLE " + TABLE_CARD
            + "("
            + TABLE_CARD_FIELD_ID + "                   INTEGER         PRIMARY KEY     AUTOINCREMENT,"
            + TABLE_CARD_FIELD_NAME + "                 TEXT    NOT NULL,"
            + TABLE_CARD_FIELD_TOTAL_AMOUNT   +"        REAL            NOT NULL,"
            + TABLE_CARD_FIELD_CURRENT_AMOUNT +"        REAL            NOT NULL,"
            + TABLE_CARD_FIELD_ICON +"                  INTEGER            NOT NULL"
            + ")";


    //AGENDA Table- column's name
    static final String TABLE_AGENDA_FIELD_ID="agenda_id";
    static final String TABLE_AGENDA_FIELD_NAME="agenda_name";
    static final String TABLE_AGENDA_FIELD_DATE="agenda_date";
    static final String TABLE_AGENDA_FIELD_TIME="agenda_time";
    static final String TABLE_AGENDA_FIELD_REMINDER="agenda_reminder";
    // AGNEDA table - CREATE
    private static final String CREATE_TABLE_AGENDA = "CREATE TABLE " + TABLE_AGENDA
            + "("
            + TABLE_AGENDA_FIELD_ID + "             INTEGER     PRIMARY KEY     AUTOINCREMENT,"
            + TABLE_AGENDA_FIELD_NAME + "           TEXT       NOT NULL,"
            + TABLE_AGENDA_FIELD_DATE   +"          NUMERIC    NOT NULL,"
            + TABLE_AGENDA_FIELD_TIME   +"          NUMERIC    NOT NULL,"
            + TABLE_AGENDA_FIELD_REMINDER + "       NUMERIC "
            + ")";

    // USER Table - column's name
    static final String TABLE_USER_FIELD_ID = "user_id";
    static final String TABLE_USER_FIELD_FIRSTNAME="user_firstname";
    static final String TABLE_USER_FIELD_LASTNAME="user_lastname";
    static final String TABLE_USER_FIELD_USERNAME="user_username";
    static final String TABLE_USER_FIELD_PASSWORD="user_password";
    static final String TABLE_USER_FIELD_EMAIL="user_email";
    static final String TABLE_USER_FIELD_SEC_QUESTION_1="user_sec_question_one";
    static final String TABLE_USER_FIELD_SEC_ANSWER_1="user_sec_answer_two";
    static final String TABLE_USER_FIELD_SEC_QUESTION_2="user_sec_question_two";
    static final String TABLE_USER_FIELD_SEC_ANSWER_2="user_answer_question_two";
    static final String TABLE_USER_FIELD_MOBILE="user_mobile";
    static final String TABLE_USER_FIELD_DOB="user_dob";
    static final String TABLE_USER_FIELD_COUNTRY="user_country";
    static final String TABLE_USER_FIELD_ORGANIZATION="user_organization";
    static final String TABLE_USER_FIELD_OCCUPATION="user_occupation";
    // USER table - CREATE
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER
            + "("
            + TABLE_USER_FIELD_ID + "                   INTEGER           PRIMARY KEY     AUTOINCREMENT,"
            + TABLE_USER_FIELD_FIRSTNAME + "            TEXT      NOT NULL,"
            + TABLE_USER_FIELD_LASTNAME   +"            TEXT      NOT NULL,"
            + TABLE_USER_FIELD_USERNAME + "             TEXT      NOT NULL UNIQUE,"
            + TABLE_USER_FIELD_PASSWORD + "             TEXT      NOT NULL,"
            + TABLE_USER_FIELD_EMAIL + "                TEXT      ,"
            + TABLE_USER_FIELD_SEC_QUESTION_1+ "        TEXT        NOT NULL,"
            + TABLE_USER_FIELD_SEC_ANSWER_1+"           TEXT        NOT NULL,"
            + TABLE_USER_FIELD_SEC_QUESTION_2+"         TEXT        NOT NULL,"
            + TABLE_USER_FIELD_SEC_ANSWER_2+"           TEXT        NOT NULL,"
            + TABLE_USER_FIELD_MOBILE + "               NUMERIC,"
            + TABLE_USER_FIELD_DOB +"                   NUMERIC, "
            + TABLE_USER_FIELD_COUNTRY+"                TEXT ,"
            + TABLE_USER_FIELD_ORGANIZATION + "         TEXT,"
            + TABLE_USER_FIELD_OCCUPATION + "           TEXT"
            + ")";
    //--------------END---------------------------//






    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext() );

        sqlDB = dbHelper.getReadableDatabase();

        if(sqlDB !=null){
            return true;
        }
        return false;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (uriMatcher.match(uri)){
            case URI_CODE_INCOME:
                queryBuilder.setTables(TABLE_INCOME);
                queryBuilder.setProjectionMap(values);
                break;
            case URI_CODE_EXPENSE:
                queryBuilder.setTables(TABLE_EXPENSE);
                queryBuilder.setProjectionMap(values);
                break;
            case URI_CODE_AGENDA:
                queryBuilder.setTables(TABLE_AGENDA);
                queryBuilder.setProjectionMap(values);
                break;
            case URI_CODE_CARD:
                queryBuilder.setTables(TABLE_CARD);
                queryBuilder.setProjectionMap(values);
                break;
            case URI_CODE_CATEGORY:
                queryBuilder.setTables(TABLE_CATEGORY);
                queryBuilder.setProjectionMap(values);
                break;
            case URI_CODE_USER:
                queryBuilder.setTables(TABLE_USER);
                queryBuilder.setProjectionMap(values);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }

        Cursor cursor = queryBuilder.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder );

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case URI_CODE_INCOME:
                return "vnd.android.cursor.dir/tableIncome";
            case URI_CODE_EXPENSE:
                return "vnd.android.cursor.dir/tableExpense";
            case URI_CODE_AGENDA:
                return "vnd.android.cursor.dir/tableAgenda";
            case URI_CODE_CARD:
                return "vnd.android.cursor.dir/tableCard";
            case URI_CODE_CATEGORY:
                return "vnd.android.cursor.dir/tableCategory";
            case URI_CODE_USER:
                return "vnd.android.cursor.dir/tableUser";
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri _uri = null;

        switch (uriMatcher.match(uri)){
            case URI_CODE_INCOME:
                long _ID1 = sqlDB.insert(TABLE_INCOME, "", values);
                if(_ID1 > 0){
                    _uri = ContentUris.withAppendedId(URI_INCOME, _ID1);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case URI_CODE_EXPENSE:
                long _ID2 = sqlDB.insert(TABLE_EXPENSE, "", values);
                if(_ID2 > 0){
                    _uri = ContentUris.withAppendedId(URI_EXPENSE, _ID2);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case URI_CODE_AGENDA:
                long _ID3 = sqlDB.insert(TABLE_AGENDA, "", values);
                if(_ID3 > 0){
                    _uri = ContentUris.withAppendedId(URI_AGENDA, _ID3);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case URI_CODE_CARD:
                long _ID4 = sqlDB.insert(TABLE_CARD, "", values);
                if(_ID4 > 0){
                    _uri = ContentUris.withAppendedId(URI_AGENDA, _ID4);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case URI_CODE_CATEGORY:
                long _ID5 = sqlDB.insert(TABLE_CATEGORY, "", values);
                if(_ID5 > 0){
                    _uri = ContentUris.withAppendedId(URI_CATEGORY, _ID5);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case URI_CODE_USER:
                long _ID6 = sqlDB.insert(TABLE_USER, "", values);
                if(_ID6 > 0){
                    _uri = ContentUris.withAppendedId(URI_USER, _ID6);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }

        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted = 0;

        switch (uriMatcher.match(uri)){
            case URI_CODE_INCOME:
                rowsDeleted = sqlDB.delete(TABLE_INCOME, selection, selectionArgs);
                break;
            case URI_CODE_EXPENSE:
                rowsDeleted = sqlDB.delete(TABLE_EXPENSE, selection, selectionArgs);
                break;
            case URI_CODE_AGENDA:
                rowsDeleted = sqlDB.delete(TABLE_AGENDA, selection, selectionArgs);
                break;
            case URI_CODE_CARD:
                rowsDeleted = sqlDB.delete(TABLE_CARD, selection, selectionArgs);
                break;
            case URI_CODE_CATEGORY:
                rowsDeleted = sqlDB.delete(TABLE_CATEGORY, selection, selectionArgs);
                break;
            case URI_CODE_USER:
                rowsDeleted = sqlDB.delete(TABLE_USER, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int rowsUpdated = 0;

        switch (uriMatcher.match(uri)){
            case URI_CODE_INCOME:
                rowsUpdated = sqlDB.update(TABLE_INCOME ,values, selection, selectionArgs);
                break;
            case URI_CODE_EXPENSE:
                rowsUpdated = sqlDB.update(TABLE_EXPENSE ,values, selection, selectionArgs);
                break;
            case URI_CODE_AGENDA:
                rowsUpdated = sqlDB.update(TABLE_AGENDA, values, selection, selectionArgs);
                break;
            case URI_CODE_CARD:
                rowsUpdated = sqlDB.update(TABLE_CARD, values, selection, selectionArgs);
                break;
            case URI_CODE_CATEGORY:
                rowsUpdated = sqlDB.update(TABLE_CATEGORY, values, selection, selectionArgs);
                break;
            case URI_CODE_USER:
                rowsUpdated = sqlDB.update(TABLE_USER, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }



    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper (Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);


        }

        @Override
        public void onCreate(SQLiteDatabase sqlDB) {
            sqlDB.execSQL(CREATE_TABLE_INCOME);
            sqlDB.execSQL(CREATE_TABLE_EXPENSE);
            sqlDB.execSQL(CREATE_TABLE_AGENDA);
            sqlDB.execSQL(CREATE_TABLE_CARD);
            sqlDB.execSQL(CREATE_TABLE_CATEGORY);
            sqlDB.execSQL(CREATE_TABLE_USER);


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {

            sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
            sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
            sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
            sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
            sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
            sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);


            onCreate(sqlDB);
        }

    }

}