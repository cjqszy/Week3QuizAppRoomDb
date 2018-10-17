package com.example.yzeng.Week3AssignYixin.data.source.roomdb;

public class RoomDAODriver  {
    MyRoomDatabase db;
    private RoomDAO myRoomDao;

    public void getRoomDb() {
//        db = MyRoomDatabase.getDatabase(this);
        myRoomDao = db.wordDao();
    }
}
