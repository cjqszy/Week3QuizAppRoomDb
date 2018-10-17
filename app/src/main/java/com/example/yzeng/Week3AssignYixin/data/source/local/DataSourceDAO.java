package com.example.yzeng.Week3AssignYixin.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yzeng.Week3AssignYixin.data.TodoNote;
import com.example.yzeng.Week3AssignYixin.data.source.TaskDataSource;


import static com.example.yzeng.Week3AssignYixin.data.source.local.DataSourceContract.TodoEntry.TABLE_NAME;

public class DataSourceDAO implements TaskDataSource {

    SQLiteDatabase sqLiteDatabase;
    SQLiteOpenHelper dataSourceDbHelper;

    public DataSourceDAO(Context context) {
        dataSourceDbHelper = new DataSourceDbHelper(context);
        openDB();
    }

    public void openDB(){
        sqLiteDatabase = dataSourceDbHelper.getWritableDatabase();
    }

    public void closeDB(){
        sqLiteDatabase.close();
    }

    @Override
    public void dbInitializer() {
        ContentValues values = new ContentValues();
        TodoNote todoNote = new TodoNote("Question1 who is not Android trainer", "Ansari", "Shiva",
                "abdul", "navneet");
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_QUESTION, todoNote.getQuestion());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER1, todoNote.getAnswer1());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER2, todoNote.getAnswer2());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER3, todoNote.getAnswer3());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER4, todoNote.getAnswer4());
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values.clear();
        TodoNote todoNote1 = new TodoNote("Question2 which 3 training program is available at RJT", "android", "IOS",
               "java", "TensorFlow");
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_QUESTION, todoNote1.getQuestion());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER1, todoNote1.getAnswer1());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER2, todoNote1.getAnswer2());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER3, todoNote1.getAnswer3());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER4, todoNote1.getAnswer4());
        sqLiteDatabase.insert(TABLE_NAME, null, values);


        values.clear();
        TodoNote todoNote2 = new TodoNote("Question3 what is your favorite launch at RJT", "Chipotle", "Spaghetti",
                "Portillo's ", "PandaExpress");
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_QUESTION, todoNote2.getQuestion());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER1, todoNote2.getAnswer1());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER2, todoNote2.getAnswer2());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER3, todoNote2.getAnswer3());
        values.put(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER4, todoNote2.getAnswer4());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public void getQuesAndAnsFromDB(TodoNoteCallBack callBack, int cursorPositionPointer) {
        Cursor cursor = sqLiteDatabase.query(DataSourceContract.TodoEntry.TABLE_NAME, null, null, null, null,
            null, null);

        if (cursorPositionPointer < 0) {
            return;
        }
        if (cursorPositionPointer > 2) {//totally 3 question so max is 2. 3 will be null pointer
            callBack.positionDecrease();
            return;
        }

        cursor.moveToPosition(cursorPositionPointer);
        int questionIndex = cursor.getColumnIndexOrThrow(DataSourceContract.TodoEntry.COLUMN_NAME_QUESTION);
        int answerIndex1 = cursor.getColumnIndexOrThrow(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER1);
        int answer2Index = cursor.getColumnIndexOrThrow(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER2);
        int answer3Index = cursor.getColumnIndexOrThrow(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER3);
        int answer4Index = cursor.getColumnIndexOrThrow(DataSourceContract.TodoEntry.COLUMN_NAME_ANSWER4);

        String question = cursor.getString(questionIndex);
        String answer1 = cursor.getString(answerIndex1);
        String answer2 = cursor.getString(answer2Index);
        String answer3 = cursor.getString(answer3Index);
        String answer4 = cursor.getString(answer4Index);

        TodoNote todoNote = new TodoNote(question, answer1, answer2, answer3, answer4);

        callBack.ShowQuiz(todoNote);

    }

  @Override
    public  void getAll(QuestionsCallBack questionsCallBack) {
      Cursor cursor =   sqLiteDatabase.query(DataSourceContract.TodoEntry.TABLE_NAME, null, null, null, null,
                null, null);
      questionsCallBack.getAllQuestions(cursor);

    }
}
