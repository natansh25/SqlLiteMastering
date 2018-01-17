package com.example.natan.sqllitemastering.Adapter;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.natan.sqllitemastering.Database.NotesContract;

/**
 * Created by natan on 1/17/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NoteTaking.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + NotesContract.NotesEntry.TABLE_NAME + " (" +
                        NotesContract.NotesEntry._ID + " INTEGER PRIMARY KEY," +
                        NotesContract.NotesEntry.COLUMN_NAME_TITLE + " TEXT," +
                        NotesContract.NotesEntry.COLUMN_NAME_NOTES + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
