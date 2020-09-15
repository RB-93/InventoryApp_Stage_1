package com.rohit.examples.android.bookstore.Data;

import android.provider.BaseColumns;

/*
 * Contract class to store constants defining Names for URIs, tables and columns.
 */
public class BookContract {

    /**
     * Implemented the BaseColumns interface by which inner class can inherit a primary key field _ID
     */
    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "Books";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BOOK_NAME = "book_title";
        public static final String COLUMN_BOOK_AUTHOR = "book_author";
        public static final String COLUMN_BOOK_PRICE = "book_price";
        public static final String COLUMN_BOOK_QUANTITY = "book_quantity";
        public static final String COLUMN_BOOK_SUPPLIER_NAME = "book_supplier_name";
        public static final String COLUMN_BOOK_SUPPLIER_PHONE_NUMBER = "book_supplier_phone_number";
    }
}