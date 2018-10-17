package com.example.yzeng.Week3AssignYixin.main;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yzeng.Week3AssignYixin.data.source.roomdb.DbSchema;
import com.example.yzeng.Week3AssignYixin.data.source.roomdb.MyRoomDatabase;
import com.example.yzeng.Week3AssignYixin.data.source.roomdb.RoomDAO;
import com.example.yzeng.Week3AssignYixin.list.ListFragment;
import com.example.yzeng.Week3AssignYixin.QuizQuestionFragment;
import com.example.yzeng.Week3AssignYixin.R;
import com.example.yzeng.Week3AssignYixin.data.TodoNote;


//YixinReview mark
public class MainActivity extends AppCompatActivity implements MainContract.View,ListFragment.OnQuestionClickListener {

    TextView quizQuestion;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    MainPresenter presenter;
    public int cursorPosition = 0;
    MyRoomDatabase db;
    private RoomDAO myRoomDao;
    private TodoNote[] todoNote = new TodoNote[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
//        presenter.initializer();

        presenter.roomDbInitialzer();

        quizQuestion = findViewById(R.id.questionTextView);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

//        presenter.getquizQA(cursorPosition);

    }

    @Override
    public void roomDbInitializer() {
        db = MyRoomDatabase.getDatabase(this);
        myRoomDao = db.wordDao();
        DbSchema exampleSchema = new DbSchema("Who is SandeepBerry?", "Sandeep", "Berry",
                "Chidie", "YiXin");
        DbSchema exampleSchema2 = new DbSchema("Who is aaa", "a", "b",
                "c", "d");
        DbSchema exampleSchema3 = new DbSchema("Who is bbb?", "b", "c",
                "d", "a");
        insert(exampleSchema);
        insert(exampleSchema2);
        insert(exampleSchema3);

        getAsyncTask getTask = new getAsyncTask(myRoomDao);
        getTask.execute();
    }

    public void insert (DbSchema exampleSchema) {
        new insertAsyncTask(myRoomDao).execute(exampleSchema);
    }

    private static class insertAsyncTask extends AsyncTask<DbSchema, Void, Void> {

        private RoomDAO mAsyncTaskDao;

        insertAsyncTask(RoomDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DbSchema... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private class getAsyncTask extends AsyncTask<Void, Void ,Void>
    {

        private RoomDAO mAsyncTaskDao;

        public getAsyncTask(RoomDAO myRoomDao) {
            this.mAsyncTaskDao = myRoomDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] question = myRoomDao.getQuestion();
            String[] option1 = myRoomDao.getOption1();
            String[] option2 = myRoomDao.getOption2();
            String[] option3 = myRoomDao.getOption3();
            String[] option4 = myRoomDao.getOption4();
            for (int i = 0; i < 3; i++) {
                todoNote[i] = new TodoNote(question[i], option1[i], option2[i], option3[i], option4[i]);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            quizQuestion.setText(todoNote[0].getQuestion());
            checkBox1.setText(todoNote[0].getAnswer1());
            checkBox2.setText(todoNote[0].getAnswer2());
            checkBox3.setText(todoNote[0].getAnswer3());
            checkBox4.setText(todoNote[0].getAnswer4());
        }
    }

    public void buttonListener2(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                if (cursorPosition == 2) {
                    return;
                }
                cursorPosition++;
                quizQuestion.setText(todoNote[cursorPosition].getQuestion());
                checkBox1.setText(todoNote[cursorPosition].getAnswer1());
                checkBox2.setText(todoNote[cursorPosition].getAnswer2());
                checkBox3.setText(todoNote[cursorPosition].getAnswer3());
                checkBox4.setText(todoNote[cursorPosition].getAnswer4());
                break;
            case R.id.prevButton:
                if (cursorPosition == 0) {
                    return;
                }
                cursorPosition--;
                quizQuestion.setText(todoNote[cursorPosition].getQuestion());
                checkBox1.setText(todoNote[cursorPosition].getAnswer1());
                checkBox2.setText(todoNote[cursorPosition].getAnswer2());
                checkBox3.setText(todoNote[cursorPosition].getAnswer3());
                checkBox4.setText(todoNote[cursorPosition].getAnswer4());
                break;
        }
    }






    @Override
    public void Showquiz(TodoNote todonote) {
        quizQuestion.setText(todonote.getQuestion());
        checkBox1.setText(todonote.getAnswer1());
        checkBox2.setText(todonote.getAnswer2());
        checkBox3.setText(todonote.getAnswer3());
        checkBox4.setText(todonote.getAnswer4());
    }

    @Override
    public void positionIncreaseComfirm() {
        cursorPosition++;
    }

    @Override
    public void positionDecreaseComfirm() {
        if (cursorPosition == 0) {
            return;
        }
        cursorPosition--;
    }

    public void buttonListener(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                presenter.positionIncrease();
                presenter.getquizQA(cursorPosition);
                break;
            case R.id.prevButton:
                presenter.positionDecrease();
                presenter.getquizQA(cursorPosition );
                break;
        }
    }

    @Override
    public void onQuestionClick(String question) {
        QuizQuestionFragment questionFragment =
                (QuizQuestionFragment) getFragmentManager().findFragmentById(R.id.fragment_quiz);
        questionFragment.updateQuestion(question);

    }
}
