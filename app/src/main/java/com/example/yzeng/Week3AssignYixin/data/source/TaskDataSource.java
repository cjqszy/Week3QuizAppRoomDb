package com.example.yzeng.Week3AssignYixin.data.source;

import android.database.Cursor;

import com.example.yzeng.Week3AssignYixin.data.TodoNote;

public interface TaskDataSource {
    void dbInitializer();

    void getQuesAndAnsFromDB(TodoNoteCallBack callBack, int cursorPosition);

     void getAll(QuestionsCallBack questionsCallBack);

//    void roomDbInitializer();

    interface  QuestionsCallBack{
      void  getAllQuestions(Cursor cursor);
    }


    interface TodoNoteCallBack{
        void ShowQuiz(TodoNote todonote);

        void positionDecrease();
    }
}
