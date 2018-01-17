package com.example.natan.sqllitemastering.Database;

import android.provider.BaseColumns;

/**
 * Created by natan on 1/17/2018.
 */

public class NotesContract {


    private NotesContract() {
    }

    public static class NotesEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_NOTES = "notes";

    }


}
