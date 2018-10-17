package com.example.yzeng.Week3AssignYixin.data.source.local;

import android.provider.BaseColumns;

public final class DataSourceContract {

    public DataSourceContract() {}

    public static abstract class TodoEntry implements BaseColumns {

        public static final String TABLE_NAME = "QuesAndAns";
        public static final String COLUMN_NAME_QUESTION = "question";
        public static final String COLUMN_NAME_ANSWER1 = "answer";
        public static final String COLUMN_NAME_ANSWER2 = "answer2";
        public static final String COLUMN_NAME_ANSWER3 = "answer3";
        public static final String COLUMN_NAME_ANSWER4 = "answer4";
    }
}
