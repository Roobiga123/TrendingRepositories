package com.example.repo.Database;

import android.provider.BaseColumns;

class RepTable {
    public static final class RepoEntry implements BaseColumns {

        public static final String TABLE_NAME = "RepTable";
        public static final String COLUMN_REPID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_LANG= "lang";
        public static final String COLUMN_RATING = "rating";
    }
}
