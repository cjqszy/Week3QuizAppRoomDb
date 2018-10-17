package com.example.yzeng.Week3AssignYixin.data.source.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RoomDAO {
    @Insert
    void insert(DbSchema schema);

    @Query("DELETE FROM room_table")
    void deleteAll();

    @Query("SELECT question from room_table ")
    String[] getQuestion();

    @Query("SELECT option1 from room_table ")
    String[] getOption1();

    @Query("SELECT option2 from room_table ")
    String[] getOption2();

    @Query("SELECT option3 from room_table ")
    String[] getOption3();

    @Query("SELECT option4 from room_table ")
    String[] getOption4();


}
