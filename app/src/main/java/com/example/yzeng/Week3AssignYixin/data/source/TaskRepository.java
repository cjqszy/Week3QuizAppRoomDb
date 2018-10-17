package com.example.yzeng.Week3AssignYixin.data.source;

import android.content.Context;
import android.database.Cursor;

import com.example.yzeng.Week3AssignYixin.data.source.local.DataSourceDAO;
import com.example.yzeng.Week3AssignYixin.data.source.roomdb.MyRoomDatabase;
import com.example.yzeng.Week3AssignYixin.data.source.roomdb.RoomDAO;
import com.example.yzeng.Week3AssignYixin.data.source.roomdb.RoomDAODriver;

public class TaskRepository implements TaskDataSource {

    TaskDataSource dataSourceDao;
    RoomDAODriver roomDAODriver;

    public TaskRepository(Context context) {
        dataSourceDao = new DataSourceDAO(context);
    }

    @Override
    public void dbInitializer() {
        dataSourceDao.dbInitializer();
    }

    @Override
    public void getQuesAndAnsFromDB(TodoNoteCallBack callBack, int cursorPosition) {
        dataSourceDao.getQuesAndAnsFromDB(callBack, cursorPosition);
    }

    @Override
    public void getAll(QuestionsCallBack questionsCallBack) {
        dataSourceDao.getAll(questionsCallBack);
    }

/*    @Override
    public void roomDbInitializer() {
        roomDAODriver.getRoomDb();
    }*/


}
