package com.rohit.examples.android.bookstore.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rohit.examples.android.bookstore.Data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {

    // Assigning a name to the database schema
    private static final String DATABASE_NAME = "books.db";

    // Database version to keep track if the schema is changed, the database version is incremented
    private static final int DATABASE_VERSION = 1;

    // Handling Log calls to observe data insertion
    private static final String LOG_TAG = BookDbHelper.class.getSimpleName();

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method to create database for the first time
     *
     * @param sqLiteDatabase to create, execute SQL commands and perform other database tasks
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
         *  Database table creation with constant column fields
         */
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BookContract.BookEntry.TABLE_NAME + "("
                + BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                + BookEntry.COLUMN_BOOK_AUTHOR + " TEXT NOT NULL, "
                + BookEntry.COLUMN_BOOK_PRICE + " REAL NOT NULL, "
                + BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL, "
                + BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";

        Log.i(LOG_TAG, SQL_CREATE_BOOKS_TABLE);

        // Execute the SQL statement if its not a SELECT or another SQL statement that returns data
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE);

    }

    /**
     * Method when database needs to be upgraded
     *
     * @param sqLiteDatabase to create, execute SQL commands and perform other database tasks
     * @param oldVer         for old database version
     * @param newVer         for new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
    }
}