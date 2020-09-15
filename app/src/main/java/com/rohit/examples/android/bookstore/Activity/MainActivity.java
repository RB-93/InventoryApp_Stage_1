package com.rohit.examples.android.bookstore.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.examples.android.bookstore.Data.BookContract.BookEntry;
import com.rohit.examples.android.bookstore.Data.BookDbHelper;
import com.rohit.examples.android.bookstore.R;

import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.COLUMN_BOOK_AUTHOR;
import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.COLUMN_BOOK_NAME;
import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.COLUMN_BOOK_PRICE;
import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.COLUMN_BOOK_QUANTITY;
import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.COLUMN_BOOK_SUPPLIER_NAME;
import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER;
import static com.rohit.examples.android.bookstore.Data.BookContract.BookEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    // View variable declaration present on screen
    private TextView textView;

    // Database helper class instance to manage database creation
    private BookDbHelper bookDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting view IDs of the widgets
        textView = findViewById(R.id.textview);

        // Instantiating DB helper class withe the current context
        bookDbHelper = new BookDbHelper(this);

        // Method call to display data inserted into database
        displayData();
    }

    // Inflating the menu resource onto the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handling overflow option selection and passing the intended methods
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_book:
                insertBook();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method definition to insert Data into Database using constants column fields
    private void insertBook() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookEntry.COLUMN_BOOK_NAME, "Harry Potter and the Sorcerers' Stone");
        contentValues.put(BookEntry.COLUMN_BOOK_AUTHOR, "J K Rowling");
        contentValues.put(BookEntry.COLUMN_BOOK_PRICE, 1000);
        contentValues.put(BookEntry.COLUMN_BOOK_QUANTITY, 10);
        contentValues.put(BookEntry.COLUMN_BOOK_SUPPLIER_NAME, "Warner Bros.");
        contentValues.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER, "+1541641842");

        // Instantiating SQLiteDatabase object to create and/or open a database that will be used for writing and reading
        SQLiteDatabase sqLiteDatabase = bookDbHelper.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(MainActivity.this, "Row inserted", Toast.LENGTH_SHORT).show();
        displayData();
    }

    // Method definition to display data inserted by user
    private void displayData() {
        textView.setText(null);

        // Instantiating SQLiteDatabase object to create and/or open a database
        SQLiteDatabase sqlDb = bookDbHelper.getReadableDatabase();

        // Querying the given URL, returning a Cursor over the result set
        Cursor cursor = sqlDb.query(TABLE_NAME, null, null, null, null, null, null, null);

        // Check to observe if the Cursor is on the first row
        if (cursor.moveToFirst()) {
            do {
                // Assigning the data to the variables by fetching & returning zero-based index for given column name
                String book_name = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_NAME));
                String book_author = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_AUTHOR));
                int book_price = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOK_PRICE));
                int book_quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOK_QUANTITY));
                String book_sup_name = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_SUPPLIER_NAME));
                String book_sup_phone = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_SUPPLIER_PHONE_NUMBER));

                // Concatenating all the data into a single variable
                String details = book_name + "\t" + book_author + "\t" + book_price + "\t" + book_quantity + "\t" + book_sup_name + "\t" + book_sup_phone + "\n\n";
                textView.append(details);

            }
            while (cursor.moveToNext()); // Move the Cursor to the next row until all the data is retrieved
        }

        // Closing the Cursor, releasing all of its resources and making it completely invalid
        cursor.close();
    }
}