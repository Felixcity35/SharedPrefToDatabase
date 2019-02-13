package com.example.felixcity.sharedpreftodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SharedPref.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = DbHelper.class.getSimpleName();
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASS = "pass";

    public static final String Create_Table = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EMAIL
            + " TEXT NOT NULL, " + COLUMN_PASS + " TEXT NOT NULL );";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);
        long id = db.insert(TABLE_NAME, null, values);
        Log.d(TAG, "user inserted " + id);
    }

    public Boolean getUser(String email, String password) {
     //   String selectQuery = "select * from " + TABLE_NAME + " where " + COLUMN_EMAIL + " = " + " ' " + email + " and " + COLUMN_PASS +
     //           " = " + " ' " + password + " ' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE "+ COLUMN_EMAIL + " =? AND "+ COLUMN_PASS + " =? ",new String[] {email,password});
        if (cursor != null) {
            // cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
               // Toast.makeText(,"Login Success ",Toast.LENGTH_LONG).show();
                return true;
            }

        }
       assert cursor != null;
       cursor.close();
      //  db.close();
        return false;
    }
}
