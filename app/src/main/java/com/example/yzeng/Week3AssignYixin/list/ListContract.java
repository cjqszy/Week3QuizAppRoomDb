package com.example.yzeng.Week3AssignYixin.list;

import android.database.Cursor;

public interface ListContract {
    interface  View{
        void setData(Cursor cursor);
    }
    interface  Presenter{
        void getData();

    }
}
